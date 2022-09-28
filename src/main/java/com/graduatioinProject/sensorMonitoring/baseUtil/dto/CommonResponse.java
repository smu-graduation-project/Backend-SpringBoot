package com.graduatioinProject.sensorMonitoring.baseUtil.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonResponse {

	SUCCESS(1, "성공")
	, FAIL(-1, "실패");

	private final int code;
	private final String message;
}
