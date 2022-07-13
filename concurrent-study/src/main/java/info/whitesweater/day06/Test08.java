package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test08")
public class Test08 {

    //易变
    volatile static boolean run = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (run){

            }
        },"t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("change...");
        run = false;


    }
}
