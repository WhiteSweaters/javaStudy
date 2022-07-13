package info.whitesweater.day02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理实例   关键类：Proxy  对应方法：newProxyInstance()
 *                  实现所对应的InvocationHandler接口
 *
 * 致命缺点：只能代理实现了接口的类  为了优化这个问题我们可以用CGLIB动态代理机制来避免
 */
public class MyProxy {

    public static void main(String[] args) {

        MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(MyProxy.class.getClassLoader(), new Class[]{MyInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("哈哈");
                Object res = method.invoke(new MyImpl(), args);
                System.out.println("开心一点咯");
                return res;
            }
        });

        proxyInstance.laugh("不要陷于思想的陷阱");
    }
}
