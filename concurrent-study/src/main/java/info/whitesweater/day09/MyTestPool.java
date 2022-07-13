package info.whitesweater.day09;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.MyTestPool")
public class MyTestPool {

    public static void main(String[] args) {
        ThreadPool2 threadPool2 = new ThreadPool2(2, 1000, TimeUnit.MILLISECONDS, 5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            threadPool2.execute(() -> {
                log.debug("{}", finalI);
            });
        }
    }

}

@Slf4j(topic = "c.ThreadPool2")
class ThreadPool2 {

    //  阻塞线程  包含任务队列
    private BlockingQueue2<Runnable> blockingQueue2;

    //  线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //  线程核心数  控制创建线程的多少
    private int cores;

    //  等待超时时间
    private long timeout;

    // 时间单位
    private TimeUnit timeUnit;


    public ThreadPool2(int cores, long timeout, TimeUnit timeUnit, int queueSize) {
        this.blockingQueue2 = new BlockingQueue2<>(queueSize);
        this.cores = cores;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < cores) {
                //  创建worker线程
                Worker worker = new Worker(task);
                log.debug("新增对象：{}，{}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                log.debug("加入任务队列：{}", task);
                blockingQueue2.put(task);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || ((task = blockingQueue2.poll(1000,TimeUnit.MILLISECONDS)) != null)) {
                try {
                    log.debug("正在执行...{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.debug("worker{}被移除...", this);
                workers.remove(this);
            }
        }
    }

}

@Slf4j(topic = "c.BlockingQueue2")
class BlockingQueue2<T> {

    //  维护任务的双向链表
    private Deque<T> queue = new ArrayDeque<>();

    //  锁对象
    private ReentrantLock lock = new ReentrantLock();

    //  条件变量：任务链表为空
    private Condition emptyWaitSet = lock.newCondition();

    //  条件变量：任务链表已满
    private Condition fullWaitSet = lock.newCondition();

    //  阻塞队列的最大容量
    private int capacity;

    //  构造方法
    public BlockingQueue2(int capacity) {
        this.capacity = capacity;
    }

    //  有等待时间的取任务
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                try {
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T element = queue.removeFirst();
            fullWaitSet.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    //  取任务
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T element = queue.removeFirst();
            fullWaitSet.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }


    //  添任务
    public void put(T element) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //  返回阻塞队列的长度
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }


}
