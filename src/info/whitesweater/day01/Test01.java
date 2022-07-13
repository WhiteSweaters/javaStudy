package info.whitesweater.day01;

/**
 * Java中的值传递问题
 * 解释：
 * 1.值传递，在Java中向一个函数传递实参，首先我们会为将要传递的这个数据创建一个副本，然后用这个副本作为实参传递到方法中
 * 2.引用传递：在给函数传递实参时，传递的就是该数据在堆中的地址，函数内部对于形参的改变会影响到实参
 * 而Java中只存在值传递
 */
public class Test01 {

    /**
     * 这里我们主要研究的是当形参是基本数据类型的时候，这个说法是绝对没有争议的
     */
    public static void test01() {
        int num1 = 1;
        int num2 = 2;
        exchange(num1, num2);
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
    }

    public static void exchange(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    /**
     * 让人疑惑的点出现了，按道理在Java中既然只出现值传递，那为什么作为形参的arr会被改变呢？
     * 注意：我们这里是将arr的地址值拷贝了一份，当地址值作为实参传入方法内部时，我们再改变它索引在0处的数据时理所当然地也改变了原数据
     * 接着看test03
     */
    public static void test02() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        change(arr);
        System.out.println(arr[0]);
    }

    public static void change(int[] array) {
        array[0] = 0;
        System.out.println(array[0]);
    }

    static class Student {
        public String name;

        Student() {

        }

        Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void test03() {
        Student xiaoZhang = new Student("小张");
        Student xiaoHuang = new Student("小黄");
        changeName(xiaoZhang, xiaoHuang);
        System.out.println("xiaoZhang =" + xiaoZhang.getName());
        System.out.println("xiaoHuang =" + xiaoHuang.getName());
    }

    public static void changeName(Student s1, Student s2) {
        Student temp = s1;
        s1 = s2;
        s2 = temp;
        System.out.println("s1 = " + s1.getName());
        System.out.println("s2 = " + s2.getName());
    }

    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
    }
}
