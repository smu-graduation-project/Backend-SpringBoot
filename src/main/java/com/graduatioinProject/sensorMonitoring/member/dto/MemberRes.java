package com.graduatioinProject.sensorMonitoring.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberRes {
	private Long userSeq;
	private String userId;
	private String password;
	private String employeeNumber;
	private String phoneNumber;
	private String name;
	private String signupType;
	private String registDate;
	private String updateDate;
	private String activateYn;
}
