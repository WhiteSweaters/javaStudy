package info.whitesweater.day08;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;


public class Test03 {

    public static void main(String[] args) {

        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Student.class,String.class,"name");
        Student student = new Student();
        System.out.println(student);
        updater.compareAndSet(student,null,"张三");
        System.out.println(student);


    }


}

class Student{
    volatile String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
