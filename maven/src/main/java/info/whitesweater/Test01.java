package info.whitesweater;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test01 {
    public static void main(String[] args) {
        MyImpl proxy = (MyImpl) CglibProxyFactory.getProxy(MyImpl.class);
        proxy.laugh("获取知识");

        Integer[] myArray = new Integer[]{1, 2, 3, 4, 5, 6};
        List<Integer> newArray = Arrays.stream(myArray).collect(Collectors.toList());
        System.out.println(newArray);


    }
}
