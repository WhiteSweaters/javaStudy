package info.whitesweater.day03;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestSync")
public class TestSync {

    private static int count = 0;
    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) {
                    count++;
                }
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) {
                    count--;
                }
            }
        },"t2");

        t1.start();
        t2.start();
        log.debug(String.valueOf(count));

    }
}
