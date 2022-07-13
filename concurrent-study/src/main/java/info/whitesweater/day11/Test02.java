package info.whitesweater.day11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Slf4j(topic = "c.Test02")
public class Test02 {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new MyTask1(1, 5)));
    }


}

class MyTask1 extends RecursiveTask<Integer> {

    private int begin;

    private int end;

    public MyTask1(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }


    @Override
    protected Integer compute() {

        if (begin == end) {
            return begin;
        }

        if (begin - end == -1) {
            return begin + end;
        }

        int mid = (begin + end) / 2;

        MyTask1 t1 = new MyTask1(begin, mid);
        t1.fork();

        MyTask1 t2 = new MyTask1(mid + 1, end);
        t2.fork();

        return t1.join() + t2.join();
    }
}
