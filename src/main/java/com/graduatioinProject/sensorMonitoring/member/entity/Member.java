package com.graduatioinProject.sensorMonitoring.member.entity;

import com.graduatioinProject.sensorMonitoring.member.dto.MemberRes;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

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
	private String userId;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String employeeNumber;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String name;

	@Column
	private String signupType;

	@Column
	private String registDate;

	@Column
	private String updateDate;

	@Setter
	@Column
	private String activateYn;

	public MemberRes toDto() {
		return MemberRes.builder()
				.userSeq(seq)
				.userId(userId)
				.employeeNumber(employeeNumber)
				.phoneNumber(phoneNumber)
				.name(name)
				.signupType(signupType)
				.registDate(registDate)
				.updateDate(updateDate)
				.build();
	}
}
