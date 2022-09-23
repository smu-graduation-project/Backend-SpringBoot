package com.graduatioinProject.sensorMonitoring.rawData.dto;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class RawDataRequest {
	private Long nodePort;
	private String timeStamp;
	private Integer sequence;
	private Double temperature;
	private Double voltage;
	private String electricCurrent;

	public LocalDateTime extractLocalDateTime() {
		String timeString = this.timeStamp;
		//2022-09-17T19:58:09
		String[] dateTime = timeString.split("T");
		Integer[] yyyyMMdd = (Integer[]) Arrays.stream(dateTime[0].split("-")).map(Integer::parseInt).toArray();
		Integer[] hhMMss = (Integer[]) Arrays.stream(dateTime[1].split(":")).map(Integer::parseInt).toArray();

		return LocalDateTime.of(yyyyMMdd[0], yyyyMMdd[1], yyyyMMdd[2], hhMMss[0], hhMMss[1], hhMMss[2]);
	}
}
