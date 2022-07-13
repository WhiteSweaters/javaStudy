package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test03")
public class Test03 {

    static Object lock = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock) {
                log.debug("有烟没？");
                while (!hasCigarette) {
                    log.debug("没烟？没思路啊...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("这下有思路了，干活！");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                log.debug("外卖到了吗？");
                while (!hasTakeout) {
                    log.debug("饿着没体力啊...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("吃饱了，开工！");
            }
        }, "t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //送烟的到了...
        new Thread(() -> {
            synchronized (lock) {
                log.debug("烟来！");
                hasCigarette = true;
                lock.notifyAll();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (lock) {
                log.debug("亲，您的外卖到了");
                hasTakeout = true;
                lock.notifyAll();
            }
        }).start();

    }

}
