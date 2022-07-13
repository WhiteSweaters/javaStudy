package info.whitesweater.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("info.whitesweater")
@EnableAspectJAutoProxy
public class SpringConfig {


}
