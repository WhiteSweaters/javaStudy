package info.whitesweater.day12;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 读线程之间的并行执行  不需要等待对方将读锁释放掉再读取  而是可以一起执行的
 * 读线程如果与写线程 并行执行，那么写线程需要等读线程把锁释放掉了才能进行写操作  也就是说读写是互斥的
 * 写写也是互斥的
 */
@Slf4j(topic = "c.Test01")
public class Test01 {
    public static void main(String[] args) {
        DataContainer container = new DataContainer();
        new Thread(()->{
            container.read();
        },"t1").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            container.write();
        },"t2").start();
    }
}

@Slf4j(topic = "c.DataContainer")
class DataContainer{
    private Object data;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock r = rw.readLock();
    ReentrantReadWriteLock.WriteLock w = rw.writeLock();


    public Object read() {
        log.debug("获取读锁...");
        r.lock();
        try {
            log.debug("读取...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        } finally {
            log.debug("释放读锁");
            r.unlock();
        }
    }

    public void write() {
        log.debug("获取写锁...");
        w.lock();
        try {
            log.debug("写入");
        }finally {
            log.debug("释放写锁...");
            w.unlock();
        }
    }
}
