package info.whitesweater.day08;

import java.sql.Connection;

public class Test06 {

    public static void main(String[] args) {
        Pool pool = new Pool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Connection connection = pool.borrow();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    pool.free(connection);
                }
            }, "t" + i).start();
        }
    }
}
