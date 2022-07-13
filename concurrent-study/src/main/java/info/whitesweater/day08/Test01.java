package info.whitesweater.day08;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class Test01 {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(5);
//        System.out.println(i.incrementAndGet()); // ++i;
//        System.out.println(i.getAndIncrement()); // i++;
//        System.out.println(i.get());  // i

//        i.updateAndGet(x -> x * 10);
        updateAndGet(i, x -> x / 2);
        System.out.println(i.get());
    }


    public static int updateAndGet(AtomicInteger i, IntUnaryOperator operator) {
        while (true) {
            int prev = i.get();
            int next = operator.applyAsInt(prev);
            if (i.compareAndSet(prev, next)) {
                return next;
            }
        }
    }
}
