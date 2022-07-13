package info.whitesweater.day02;

public class MyImpl implements MyInterface {
    @Override
    public void laugh(String reason) {
        System.out.println("laugh for "+ reason);
    }
}
