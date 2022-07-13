package info.whitesweater.day14;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        String[] all = new String[10];
        Random random = new Random();
        CountDownLatch latch = new CountDownLatch(10);
        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            service.submit(() -> {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    all[finalJ] = i + "%";
                    System.out.print("\r" + Arrays.toString(all));
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
            System.out.println("\n游戏开始");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            service.shutdown();
        }



    }


}
