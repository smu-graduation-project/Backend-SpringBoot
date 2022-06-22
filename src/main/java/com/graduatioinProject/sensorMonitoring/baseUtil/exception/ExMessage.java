package com.graduatioinProject.sensorMonitoring.baseUtil.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExMessage {
	UNDEFINED_ERROR("미정의에러")
	, MEMBER_ERROR_NOT_FOUND("회원을 찾을 수 없습니다.")
	, MEMBER_ERROR_NOT_FOUND_ENG("CannotFoundMember")
	, MEMBER_ERROR_DUPLICATE("해당 아이디의 회원이 이미 존재합니다.")
	, MEMBER_ERROR_USER_ID_FORMAT("아이디 형식을 맞춰주세요.")
	, MEMBER_ERROR_PASSWORD("패스워드가 일치하지 않습니다.")
	, JWT_ERROR_REFRESH_TOKEN("REFRESH JWT 토큰이 잘못되었습니다.")
	, JWT_ERROR_FORMAT("JWT 토큰이 잘못되었습니다.")
	, JWT_ERROR_EXPIRED("JWT 토큰이 만료되었습니다.")
	, JWT_ACCESS_DENIED("접근이 거부되었습니다.")
	, LOGIN_ERROR_FAIL("로그인 실패")
	, DATA_ERROR_NOT_FOUND("해당 데이터를 찾을 수 없습니다.")
	,SITE_ERROR_NOT_FOUND("해당 SITE를 찾을 수 없습니다.")
	, BATTERY_ERROR_NOT_FOUND("해당 BATTERY를 찾을 수 없습니다.")
	, NODE_ERROR_NOT_FOUND("해당 NODE를 찾을 수 없습니다.")
	, SESSION_ERROR_MEMBER_NOT_FOUND("로그인 정보를 찾을 수 없습니다.")
	, IMAGE_NOT_UPLOADED("이미지 업로드에 실패하였습니다.")
	, NO_AUTHORITY("접근권한이 없습니다.")
	;

	private final String message;
}
