package info.whitesweater.day10;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 饥饿问题及其解决方案
 */
@Slf4j(topic = "c.Test02")
public class Test02 {

    private static final List<String> MENU = Arrays.asList("宫保鸡丁", "肉末茄子", "干锅包菜", "黄豆炖猪蹄");
    private static Random random = new Random();

    public static void main(String[] args) {
        ExecutorService waiterSet = Executors.newFixedThreadPool(1);
        ExecutorService cookSet = Executors.newFixedThreadPool(1);
        waiterSet.execute(() -> {
            log.debug("处理点菜...");
            Future<String> future = cookSet.submit(() -> {
                log.debug("菜已经做好了...");
                return MENU.get(random.nextInt(MENU.size()));
            });
            try {
                log.debug("上菜：{}",future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        waiterSet.execute(() -> {
            log.debug("处理点菜...");
            Future<String> future = cookSet.submit(() -> {
                log.debug("菜已经做好了...");
                return MENU.get(random.nextInt(MENU.size()));
            });
            try {
                log.debug("上菜：{}",future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

}
