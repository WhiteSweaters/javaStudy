package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test09")
public class Test09 {

    static Thread monitorThread;

    public static void main(String[] args) {
        start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop();
    }

    public static void start() {
        monitorThread = new Thread(() -> {
            while (true){
                Thread thread = Thread.currentThread();
                if(thread.isInterrupted()){
                    log.debug("料理后事...");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    thread.interrupt();
                }
            }
        },"monitor");

        monitorThread.start();
    }

    public static void stop() {
        monitorThread.interrupt();
    }
}
