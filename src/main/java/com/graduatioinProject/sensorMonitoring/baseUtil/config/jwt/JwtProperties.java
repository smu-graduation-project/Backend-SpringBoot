package com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt;

import org.springframework.beans.factory.annotation.Value;

public interface JwtProperties {
	String ID = "id";
	String USERNAME = "username";
	String TOKEN_PREFIX = "Bearer ";
	String ACCESS_TOKEN = "AccessToken";
	String REFRESH_TOKEN = "RefreshToken";
	long EXPIRATION_TIME = (1000 * 10 * 6) * 30; // 30m
	long REFRESH_EXPIRATION_TIME = 1209600; // 14days
	String HEADER_PREFIX = "Authorization";
	String REFRESH_HEADER_PREFIX = "Authorization-refresh";
}
