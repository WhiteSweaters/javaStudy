package info.whitesweater.day04;

public class Test01 {


    class GuardedObject {
        private Object res;

        public Object get() {
            synchronized (this) {
                while (res == null) {
                    try {
                        //这里注意虚假唤醒的问题
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return res;
        }

        public Object get(long timeout) {
            synchronized (this) {
                //记录开始时间
                long begin = System.currentTimeMillis();
                long passedTime = 0L;
                while (res == null) {
                    if(passedTime - timeout >= 0){
                        break;
                    }
                    try {
                        //这里注意虚假唤醒的问题
                        this.wait(timeout - passedTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    passedTime = System.currentTimeMillis() - begin;
                }
            }
            return res;
        }

        public void complete(Object res){
            synchronized (this){
                this.res = res;
                this.notifyAll();
            }
        }
    }


}
