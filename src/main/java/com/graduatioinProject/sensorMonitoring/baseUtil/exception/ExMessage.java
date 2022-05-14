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
	, SITE_ERROR_NOT_FOUND("해당 SITE를 찾을 수 없습니다.")
	, DATA_ERROR_SESSION_NOT_EXIST("세션 데이터를 확인할 수 없습니다.")
	, DATA_ERROR_MEMBER_NOT_FOUND("해당 세션의 회원을 찾을 수 없습니다.")
	;


	private final String message;
}
