package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test04")
public class Test04 {

    static ReentrantLock ROOM = new ReentrantLock();
    //新增休息室 条件对象
    static Condition waitCigaretteSet = ROOM.newCondition();
    static Condition waitTakeoutSet = ROOM.newCondition();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) {
        new Thread(() -> {
            ROOM.lock();
            log.debug("有烟吗？");
            try {
                while (!hasCigarette) {
                    log.debug("没烟没思路啊...");
                    try {
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("舒服了，干活！");
            } finally {
                ROOM.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            ROOM.lock();
            log.debug("外卖到了吗？");
            try {
                while (!hasTakeout) {
                    log.debug("饿着没法干活啊...");
                    try {
                        waitTakeoutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("吃饱了，开工！");
            } finally {
                ROOM.unlock();
            }
        }, "t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //烟到了
        new Thread(() -> {
            ROOM.lock();
            try {
                log.debug("烟来！");
                hasCigarette = true;
                waitCigaretteSet.signal();
            }finally {
                ROOM.unlock();
            }
        },"t3").start();
    }
}
