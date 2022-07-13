package info.whitesweater.day11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);

        System.out.println(pool.invoke(new MyTask(5)));
    }
}

@Slf4j(topic = "c.MyTask")
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }


    @Override
    protected Integer compute() {
        if (n == 1) {
            return 1;
        }

        MyTask t1 = new MyTask(n - 1);

        t1.fork();

        return t1.join() + n;

    }
}
