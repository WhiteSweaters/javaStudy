package info.whitesweater;

import info.whitesweater.config.SpringConfig;
import info.whitesweater.dao.UserDao;
import info.whitesweater.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App2 {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
     /*   UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.register();*/

 /*       UserService userService = context.getBean("userService", UserService.class);
        userService.register();*/

     /*
      DataSource dataSource = context.getBean("dataSource", DataSource.class);
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from tb_user");
        ResultSet resultSet = ps.executeQuery();
        List<Integer> list = new ArrayList<Integer>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            list.add(id);
        }
        System.out.println(list);
        */
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        System.out.println(dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);


    }
}
