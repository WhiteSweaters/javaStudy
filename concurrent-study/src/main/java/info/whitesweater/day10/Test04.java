package info.whitesweater.day10;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个定时任务，在每周的周四18：00执行
 */
@Slf4j(topic = "c.Test04")
public class Test04 {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        LocalDateTime now = LocalDateTime.now();
        log.debug("{}", now);
        LocalDateTime target = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        log.debug("{}", target);
        if (now.compareTo(target) > 0) {
            target = target.plusWeeks(1);
        }
        log.debug("{}", target);
        long initialDelay = Duration.between(now, target).toMillis();
        long period = 1000 * 60 * 60 * 24 * 7L;
        pool.scheduleAtFixedRate(() -> {
            log.debug("running");
        }, initialDelay, period, TimeUnit.MILLISECONDS);

    }
}
