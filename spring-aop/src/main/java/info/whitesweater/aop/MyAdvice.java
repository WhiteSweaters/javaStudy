package info.whitesweater.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

    /**
     * 切入点
     */
    @Pointcut("execution(void info.whitesweater.service.impl.UserServiceImpl.delete())")
    private void pt(){

    }

    /**
     * 切面
     */
    @Before("pt()")
    public void method(){
        System.out.println(System.currentTimeMillis());
    }

    @Pointcut("execution(void info.whitesweater.service.impl.UserServiceImpl.update())")
    private void pt2(){

    }

    @After("pt2()")
    public void method2(){
        System.out.println("自己测试 ...");
    }
}
