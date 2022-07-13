package info.whitesweater.day08;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Slf4j(topic = "c.Test05")
public class Test05 {

    /**
     * JDK8之后引入的线程安全的日期转换类：DateTimeFormatter
     * @param args
     */
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                log.debug("{}", dtf.parse("2000-09-23"));
            }, "t" + i).start();
        }
    }


    /**
     * 线程不安全的日期转换类：SimpleDateFormat
     */
    public static void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    log.debug("{}", sdf.parse("2000-09-23"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
    }
}
