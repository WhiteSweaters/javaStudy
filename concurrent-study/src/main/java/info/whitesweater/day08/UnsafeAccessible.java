package info.whitesweater.day08;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAccessible {

    public static Unsafe getUnsafe() {

        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (theUnsafe == null) {
            return null;
        }
        Unsafe unsafe = null;
        try {
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }


}
