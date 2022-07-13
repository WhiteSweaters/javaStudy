package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "c.Test07")
public class Test07 {

    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void main(String[] args) {
        ParkUnPark parkUnPark = new ParkUnPark(5);
        t1 = new Thread(() -> {
            parkUnPark.print("a", t2);
        }, "t1");
        t2 = new Thread(() -> {
            parkUnPark.print("b", t3);
        }, "t2");
        t3 = new Thread(() -> {
            parkUnPark.print("c", t1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }


}

class ParkUnPark {
    private int loopNumber;

    public ParkUnPark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread nextThread) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(nextThread);
        }
    }
}
