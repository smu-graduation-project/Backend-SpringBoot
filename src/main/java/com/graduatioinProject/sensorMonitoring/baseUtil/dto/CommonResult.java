package com.graduatioinProject.sensorMonitoring.baseUtil.dto;

import lombok.Data;

@Data
public class CommonResult {
	boolean success;

	int code;

	String message;
}
