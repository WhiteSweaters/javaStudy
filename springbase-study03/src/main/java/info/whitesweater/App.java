package info.whitesweater;

import info.whitesweater.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.bak");
        UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.register();
    }
}
