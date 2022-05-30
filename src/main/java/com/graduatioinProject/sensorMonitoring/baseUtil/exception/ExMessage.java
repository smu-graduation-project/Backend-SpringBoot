package com.graduatioinProject.sensorMonitoring.baseUtil.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExMessage {
	UNDEFINED_ERROR("미정의에러")
	, MEMBER_ERROR_NOT_FOUND("회원을 찾을 수 없습니다.")
	, MEMBER_ERROR_DUPLICATE("해당 아이디의 회원이 이미 존재합니다.")
	, MEMBER_ERROR_USER_ID_FORMAT("아이디 형식을 맞춰주세요.")
	, MEMBER_ERROR_PASSWORD("패스워드가 일치하지 않습니다.")
	, JWT_ERROR_FORMAT("JWT 토큰이 잘못되었습니다.")
	, JWT_ACCESS_DENIED("접근이 거부되었습니다.")
	;

	private final String message;
}
