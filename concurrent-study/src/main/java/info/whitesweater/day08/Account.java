package info.whitesweater.day08;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {

    private AtomicInteger balance;

    public Account() {

    }

    public Account(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    public int getAccount() {
        return balance.get();
    }

    public void withdraw(int count) {
       /* while (true) {
            int prev = balance.get();
            int next = prev - count;
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }*/
       balance.addAndGet(count);
    }
}
