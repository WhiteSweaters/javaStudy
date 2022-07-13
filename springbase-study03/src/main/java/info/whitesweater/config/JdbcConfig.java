package info.whitesweater.config;

import com.alibaba.druid.pool.DruidDataSource;
import info.whitesweater.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@PropertySource("jdbc.properties")
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driverClassName;

    // 注入第三方提供的Bean
    @Bean
    public DataSource dataSource(UserDao userDao) {
        System.out.println(userDao);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl("jdbc:mysql://101.35.97.220:3307/competition");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
