package info.whitesweater.day05;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test02")
public class Test02 {

    /**
     * 死锁演示
     * @param args 主方法参数
     */
    public static void main(String[] args) {
        Object A = new Object();
        Object B = new Object();

        new Thread(() -> {
            synchronized (A) {
                log.debug("t1线程获取A对象锁成功");
                try {
                    Thread.sleep(1000);
                    synchronized (B) {
                        log.debug("t1线程尝试获取B对象锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            log.debug("t2线程获取B对象锁成功");
            synchronized (B) {
                try {
                    Thread.sleep(1000);
                    synchronized (A) {
                        log.debug("t2线程尝试获取A对象锁...");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
