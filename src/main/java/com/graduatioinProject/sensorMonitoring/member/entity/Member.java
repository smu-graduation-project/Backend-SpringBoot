package com.graduatioinProject.sensorMonitoring.member.entity;

import com.graduatioinProject.sensorMonitoring.member.dto.MemberRes;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@Column(unique = true, nullable = false)
	private String username;			// 아이디

	@Column(nullable = false)
	private String password;			// 비밀번호

	@Column(unique = true, nullable = false)
	private String employeeNumber;		// 사번

	@Column(nullable = false)
	private String phoneNumber;			// 휴대전화 번호만

	@Column(nullable = false)
	private String realname;			// 본명

	@Column
	private String refreshToken;		// 리프레쉬 토큰

	@Column
	private String roles;				//USER,ADMIN...

	@Column
	private String signupType;			// 회원가입 타입 (Email, OAuth2.0)

	@Column
	private String createDate;			// 생성시각

	@Column
	private String updateDate;			// 수정시각

	@Setter
	@Column
	private String activateYn;			// 활성화 여부

	public List<String> getRoleList() {
		if (roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}

	public MemberRes toDto() {
		return MemberRes.builder()
				.userSeq(seq)
				.username(username)
				.role(roles)
				.employeeNumber(employeeNumber)
				.phoneNumber(phoneNumber)
				.realname(realname)
				.signupType(signupType)
				.createDate(createDate)
				.updateDate(updateDate)
				.activateYn(activateYn)
				.build();
	}
}
