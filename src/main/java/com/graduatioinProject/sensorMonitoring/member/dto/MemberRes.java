package com.graduatioinProject.sensorMonitoring.member.dto;

import com.graduatioinProject.sensorMonitoring.member.entity.Member;
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

    public Member toEntity() {
		return Member.builder()
				.seq(this.userSeq)
				.username(this.username)
				.build();
    }
}
