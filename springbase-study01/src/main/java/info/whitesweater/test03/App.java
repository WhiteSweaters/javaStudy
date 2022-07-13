package info.whitesweater.test03;

import info.whitesweater.test03.service.UserService;
import info.whitesweater.test03.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService6");
        userService.register();
    }
}
