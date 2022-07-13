package info.whitesweater.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("info.whitesweater.controller")
@Import(ServletContainerInitConfig.class)
public class SpringmvcConfig {
}
