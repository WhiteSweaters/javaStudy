package info.whitesweater;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DataSource dataSource = (DataSource) context.getBean("dataSource");
//        System.out.println(dataSource);
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.register();
    }
}
