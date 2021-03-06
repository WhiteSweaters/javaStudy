package info.whitesweater.day13;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) {
        DataContainerStamped dataContainerStamped = new DataContainerStamped(1);

        new Thread(() -> {
            dataContainerStamped.read(1);
        }, "t1").start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            dataContainerStamped.write(2);
        },"t2").start();
    }
}


@Slf4j(topic = "c.DataContainerStamped")
class DataContainerStamped {

    private int data;
    private final StampedLock lock = new StampedLock();

    public DataContainerStamped(int data) {
        this.data = data;
    }

    public int read(int readTime) {
        long stamp = lock.tryOptimisticRead();
        log.debug("optimistic read locking... {}", stamp);
        try {
            Thread.sleep(readTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (lock.validate(stamp)) {
            log.debug("read finish... {}", stamp);
            return data;
        }
        log.debug("updating to read lock... {}", stamp);
        try {
            stamp = lock.readLock();
            log.debug("read lock {}", stamp);
            try {
                Thread.sleep(readTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("read finish... {}", stamp);
            return data;
        } finally {
            log.debug("read unlock {}", stamp);
            lock.unlockRead(stamp);
        }
    }

    public void write(int newData) {
        long stamp = lock.writeLock();
        log.debug("write lock {}", stamp);
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = newData;
        } finally {
            log.debug("write unlock {}", stamp);
            lock.unlockWrite(stamp);
        }
    }
}
