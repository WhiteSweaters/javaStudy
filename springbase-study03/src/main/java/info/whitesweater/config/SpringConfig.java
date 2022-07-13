package info.whitesweater.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan("info.whitesweater.dao.impl")
@Import({JdbcConfig.class})
public class SpringConfig {

}
