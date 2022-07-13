package info.whitesweater;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test02 {

    public static void main(String[] args) {
        MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(Test02.class.getClassLoader(), new Class[]{MyInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("haha");
                Object res = method.invoke(new MyImpl(), args);
                System.out.println("haha");
                return res;
            }
        });

        proxyInstance.laugh("study");
    }
}
