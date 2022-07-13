package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test01")
public class Test01 {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("尝试获取锁...");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.debug("获取锁失败");
                e.printStackTrace();
                return;
            }
            try {
                log.debug("获取到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");



        lock.lock();
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("打断...");
        t1.interrupt();
    }
}
