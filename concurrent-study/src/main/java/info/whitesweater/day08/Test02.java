package info.whitesweater.day08;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Test02 {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> ref = new AtomicStampedReference<Integer>(5,0);
        boolean b = ref.compareAndSet(5, 6, 0, 1);
        System.out.println(ref.getReference());
        System.out.println(ref.getStamp());
    }

}
