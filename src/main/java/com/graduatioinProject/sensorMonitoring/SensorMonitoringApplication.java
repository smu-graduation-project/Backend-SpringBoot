package com.graduatioinProject.sensorMonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableAspectJAutoProxy // Enable AOP
@EnableJpaRepositories(basePackages = {"com.graduatioinProject.sensorMonitoring"}) // com.my.jpa.repository 하위에 있는 jpaRepository를 상속한 repository scan
@EntityScan(basePackages = {"com.graduatioinProject.sensorMonitoring"}) // com.my.jpa.entity 하위에 있는 @Entity 클래스 scan
public class SensorMonitoringApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SensorMonitoringApplication.class, args);
	}

}
