package com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt;

import org.springframework.beans.factory.annotation.Value;

public interface JwtProperties {
	// TODO : 임시로 토큰 만료시간 길게 설정
	long EXPIRATION_TIME = 1000 * 60 * 10 * (6 * 24 * 7); // 10m * (60분 * 24시간 * 7일)
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_PREFIX = "Authorization";
	long REFRESH_EXPIRATION_TIME = 1209600; // 14days
	String REFRESH_HEADER_PREFIX = "Authorization-refresh";
}
