package info.whitesweater.day07;

/**
 * double-checked locking 单例模式
 */
public class Test01 {

    private Test01() {

    }

    private static volatile Test01 INSTANCE = null;

    public static Test01 getInstance() {
        if (INSTANCE == null) {//t2可能拿到未执行完毕构造方法的单例 原因：t1中的指令重排序问题  解决：INSTANCE带volatile关键字
            synchronized (Test01.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Test01();//t1
                }
            }
        }
        return INSTANCE;
    }

}
