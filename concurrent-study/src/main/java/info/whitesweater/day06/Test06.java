package info.whitesweater.day06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test06")
public class Test06 {

    public static void main(String[] args) {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();
        new Thread(() -> {
            awaitSignal.print("a", a, b);
        }, "t1").start();
        new Thread(() -> {
            awaitSignal.print("b", b, c);
        }, "t2").start();
        new Thread(() -> {
            awaitSignal.print("c", c, a);
        }, "t3").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        awaitSignal.lock();
        try {
            a.signal();
        }finally {
            awaitSignal.unlock();
        }
    }

}

class AwaitSignal extends ReentrantLock {

    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition cur, Condition next) {

        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                try {
                    cur.await();
                    System.out.print(str);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                unlock();
            }
        }
    }

}
