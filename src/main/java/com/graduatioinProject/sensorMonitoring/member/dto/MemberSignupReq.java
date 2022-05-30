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
	@ApiModelProperty(required = true)
	private String username;
	@ApiModelProperty(required = true)
	private String password;
	@ApiModelProperty(required = true)
	private String password2;
	@ApiModelProperty(required = true)
	private String employeeNumber;
	@ApiModelProperty(required = true)
	private String phoneNumber;
	@ApiModelProperty(required = true)
	private String realname;

	public Member toEntity(String encryptedPw, String role) {
		return Member.builder()
				.username(username)
				.password(encryptedPw)
				.employeeNumber(employeeNumber)
				.phoneNumber(phoneNumber)
				.realname(realname)
				.roles(role)
				.createDate(new DateConfig().getNowDate())
				.updateDate(new DateConfig().getNowDate())
				.signupType("E")
				.activateYn("Y")
				.build();
	}
}
