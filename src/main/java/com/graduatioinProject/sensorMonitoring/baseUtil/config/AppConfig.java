package com.graduatioinProject.sensorMonitoring.baseUtil.config;

import com.graduatioinProject.sensorMonitoring.baseUtil.formatter.LocalDateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */
@Configuration
public class AppConfig {

    @Bean
    public LocalDateFormatter localDateFormatter() {
        return new LocalDateFormatter();
    }
}
