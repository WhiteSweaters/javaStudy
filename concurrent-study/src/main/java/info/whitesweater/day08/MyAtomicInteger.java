package info.whitesweater.day08;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MyAtomicInteger {

    private volatile static int value;
    private static final Unsafe UNSAFE;
    private static final long valueOffset;


    static {
        UNSAFE = Unsafe.getUnsafe();
        try {
            valueOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public int getValue() {
        return value;
    }

    public boolean compareAndSet(int prev, int next) {
        return UNSAFE.compareAndSwapInt(this, valueOffset, prev, next);
    }
}
