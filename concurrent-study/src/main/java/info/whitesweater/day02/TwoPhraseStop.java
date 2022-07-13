package info.whitesweater.day02;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TwoPhraseStop")
public class TwoPhraseStop {


    public static void main(String[] args) throws InterruptedException {
        MyDemo myDemo = new MyDemo();
        myDemo.start();
        Thread.sleep(3500);
        myDemo.stop();
    }


    static class MyDemo {
        private Thread thread;
        public void start() {
            this.thread = new Thread(() -> {
                while (true) {
                    Thread thread = Thread.currentThread();
                    if (thread.isInterrupted()) {
                        log.debug("料理后事");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                        log.debug("执行监控记录");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        //注意，抛出异常后打断标记会被置为假，所以想要正常终止循环，就必须重置打断标记
                        thread.interrupt();
                    }
                }
            });

            thread.start();
        }

        public void stop(){
            thread.interrupt();
        }
    }
}
