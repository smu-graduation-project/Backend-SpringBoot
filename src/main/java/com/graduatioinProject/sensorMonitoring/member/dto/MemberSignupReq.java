package com.graduatioinProject.sensorMonitoring.member.dto;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.DateConfig;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberSignupReq {
	@ApiModelProperty(required = true, value = "성함", example = "본명", position = 0)
	private String realname;
	@ApiModelProperty(required = true, value = "아이디", example = "memberId1", position = 1)
	private String username;
	@ApiModelProperty(required = true, value = "패스워드", example = "password123!", position = 2)
	private String password;
	@ApiModelProperty(required = true, value = "패스워드 확인", example = "password123!", position = 3)
	private String password2;
	@ApiModelProperty(required = true, value = "사번", example = "1", position = 4)
	private String employeeNumber;
	@ApiModelProperty(required = true, value = "연락처", example = "01077779999", position = 5)
	private String phoneNumber;

	public Member toEntity(String encryptedPw, Role role) {
		return Member.builder()
				.username(username)
				.password(encryptedPw)
				.employeeNumber(employeeNumber)
				.phoneNumber(phoneNumber)
				.realname(realname)
				.role(role)
				.createDate(new DateConfig().getNowDate())
				.updateDate(new DateConfig().getNowDate())
				.signupType("E")
				.activateYn("Y")
				.build();
	}
}
