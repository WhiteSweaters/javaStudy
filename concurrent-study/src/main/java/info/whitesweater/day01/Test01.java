package info.whitesweater.day01;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.concurrent.FutureTask;

/**
 * 同步等待
 */
@Slf4j(topic = "c.Sync")
public class Test01 {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("main method ...")).start();
        log.debug("do other things...");

        FutureTask<Integer> task = new FutureTask<>(() -> {
            return 1;
        });


        new Thread(task,"t1");

        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };

    }
}
