package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test05")
public class Test05 {
    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1, 5);
        new Thread(() -> {
            wn.print("a",1,2);
        },"t1").start();
        new Thread(() -> {
            wn.print("b",2,3);
        },"t2").start();
        new Thread(() -> {
            wn.print("c",3,1);
        },"t3").start();
    }
}

class WaitNotify {

    private int flag;
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str, int curFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (this.flag != curFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                this.flag = nextFlag;
                this.notifyAll();
            }
        }
    }

}
