package info.whitesweater.day05;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

public class Test03 {

    public static void main(String[] args) {
        Chopstick chopstick1 = new Chopstick("1");
        Chopstick chopstick2 = new Chopstick("2");
        Chopstick chopstick3 = new Chopstick("3");
        Chopstick chopstick4 = new Chopstick("4");
        Chopstick chopstick5 = new Chopstick("5");

        Philosopher t1 = new Philosopher("a", chopstick1, chopstick2);
        Philosopher t2 = new Philosopher("b", chopstick2, chopstick3);
        Philosopher t3 = new Philosopher("c", chopstick3, chopstick4);
        Philosopher t4 = new Philosopher("d", chopstick4, chopstick5);
        Philosopher t5 = new Philosopher("e", chopstick1, chopstick5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}

@Slf4j(topic = "c.Philosopher")
class Philosopher extends Thread {

    private String name;
    private Chopstick left;
    private Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }


    @Override
    public void run() {
        while (true) {
            if (left.tryLock()) {
               try {
                   if(right.tryLock()){
                       try {
                           eat();
                       }finally {
                           right.unlock();
                       }
                   }
               }finally {
                   left.unlock();
               }
            }
        }
    }

    public void eat() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("eating...");
    }

}


class Chopstick extends ReentrantLock {
    private String name;



    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "name='" + name + '\'' +
                '}';
    }
}
