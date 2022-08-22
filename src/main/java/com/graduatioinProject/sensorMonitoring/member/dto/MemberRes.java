package com.graduatioinProject.sensorMonitoring.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberRes {
	private Long userSeq;
	private String username;
	private String role;
	private String employeeNumber;
	private String phoneNumber;
	private String realname;
	private String signupType;
	private String createDate;
	private String updateDate;
	private String activateYn;
}
