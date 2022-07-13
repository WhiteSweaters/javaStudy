package info.whitesweater.day09;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger atomicInteger = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my_pool" + atomicInteger.getAndIncrement());
            }
        });

        pool.execute(() -> {
            log.debug("1...");
        });

        pool.execute(() -> {
            log.debug("2...");
        });

        pool.execute(() -> {
            log.debug("3...");
        });
    }
}
