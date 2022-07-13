package info.whitesweater.day08;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test04 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        long ageOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("age"));
        long nameOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));

        Teacher teacher = new Teacher();
        unsafe.compareAndSwapInt(teacher, ageOffset, 0, 26);
        unsafe.compareAndSwapObject(teacher, nameOffset, null, "王祖贤");

        System.out.println(teacher);


    }

}

class Teacher {
    volatile int age;
    volatile String name;


    @Override
    public String toString() {
        return "Teacher{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
