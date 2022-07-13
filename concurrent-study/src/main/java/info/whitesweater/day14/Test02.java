package info.whitesweater.day14;

import java.util.ArrayList;
import java.util.List;

public class Test02 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(8);

        for (Integer integer : list) {
            list.remove(integer);
        }

    }
}
