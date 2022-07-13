package info.whitesweater;

import info.whitesweater.config.SpringConfig;
import info.whitesweater.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App2 {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.getUserById(1));
    }
}
