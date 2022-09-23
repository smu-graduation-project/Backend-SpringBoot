package com.graduatioinProject.sensorMonitoring.rawData.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RawDataResponse {
	private Long nodePort;
	private String timeStamp;
	private Integer sequence;
	private Double temperature;
	private Double voltage;
	private String electricCurrent;
}
