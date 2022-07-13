package info.whitesweater.test02;

import info.whitesweater.test02.dao.OrderDao;
import info.whitesweater.test02.service.UserService;
import info.whitesweater.test02.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService2 = (UserService) applicationContext.getBean("userService2");
        userService2.register();
        applicationContext.registerShutdownHook();
/*        OrderDao orderDao1 = (OrderDao) applicationContext.getBean("orderDao");
        OrderDao orderDao2 = (OrderDao) applicationContext.getBean("orderDao");
//        orderDao.getOrder();
        System.out.println(orderDao1);
        System.out.println(orderDao2);*/

    }
}
