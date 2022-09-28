package com.graduatioinProject.sensorMonitoring.rawData.dto;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class RawDataRequest {
	private Long nodePort;
	private String timeStamp;
	private Long sequence;
	private Double temperature;
	private Double voltage;
	private Double electricCurrent;

	public LocalDateTime extractLocalDateTime() {
		String timeString = this.timeStamp;
		//2022-09-17T19:58:09
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		return  LocalDateTime.parse(timeString, formatter);
	}
}
