package com.graduatioinProject.sensorMonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy // Enable AOP
public class SensorMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorMonitoringApplication.class, args);
	}

}
