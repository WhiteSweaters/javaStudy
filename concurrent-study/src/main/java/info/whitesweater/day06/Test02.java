package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test02")
public class Test02 {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("尝试获取锁");
            if (!lock.tryLock()) {
                log.debug("获取不到锁");
                return;
            }
            try {
                log.debug("获取得到锁");
            }finally {
                lock.unlock();
            }
        },"t1");

        lock.lock();
        t1.start();
    }
}
