package info.whitesweater.day10;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Test03")
public class Test03 {

    public static void main(String[] args) {

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        log.debug("start...");
    /*    pool.scheduleAtFixedRate(() -> {
            log.debug("running...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);*/

        /*pool.scheduleWithFixedDelay(()->{
            log.debug("running...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1000,1000,TimeUnit.MILLISECONDS);*/

        method2(pool);

    }

    private static void method2(ScheduledExecutorService pool) {
        pool.schedule(() -> {
            log.debug("task1...");
            try {
                int i = 1 / 0;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 1000, TimeUnit.MILLISECONDS);

        pool.schedule(() -> {
            log.debug("task2...");
        }, 1000, TimeUnit.MILLISECONDS);
    }

    private static void method1() {
        Timer timer = new Timer();

        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("t1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("t2...");
            }
        };

        timer.schedule(timerTask1, 1000);
        timer.schedule(timerTask2, 1000);
    }
}
