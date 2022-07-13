package info.whitesweater.day10;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);

        String str = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    log.debug("end");
                    return "1";
                }, () -> {
                    log.debug("begin");
                    Thread.sleep(500);
                    log.debug("end");
                    return "2";
                }
                , () -> {
                    log.debug("begin");
                    Thread.sleep(2000);
                    log.debug("end");
                    return "3";
                }
        ));

        log.debug("{}",str);
    }

    private static void method2(ExecutorService pool) throws InterruptedException {
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "1";
                }, () -> {
                    log.debug("begin");
                    Thread.sleep(500);
                    return "2";
                }
                , () -> {
                    log.debug("begin");
                    Thread.sleep(2000);
                    return "3";
                }
        ));

        futures.forEach(f -> {
            try {
                log.debug(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void method1(ExecutorService pool) throws InterruptedException, ExecutionException {
        Future<String> future = pool.submit(() -> {
            log.debug("run...");
            Thread.sleep(1000);
            return "ok";
        });

        log.debug("{}", future.get());
    }

}
