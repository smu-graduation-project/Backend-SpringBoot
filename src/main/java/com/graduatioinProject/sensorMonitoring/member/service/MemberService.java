package com.graduatioinProject.sensorMonitoring.member.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberReq;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberRes;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public List<MemberRes> findAll() {
		return memberRepository.findAll()
				.stream()
				.map(Member::toDto)
				.collect(Collectors.toList());
	}

	@Transactional
	public Member save(MemberReq memberRequestDto) {
		if (memberRepository.existsByUserId(memberRequestDto.getUserId())) {
			throw new BussinessException(ExMessage.MEMBER_ERROR_DUPLICATE);
		}
		return memberRepository.save(
				memberRequestDto.toEntity(passwordEncoder.encode(memberRequestDto.getPassword()))
		);
	}

	@Transactional(readOnly = true)
	public MemberRes findByUserId(String userId) {
		return memberRepository.findByUserId(userId)
				.orElseThrow(() -> new BussinessException(ExMessage.MEMBER_ERROR_NOT_FOUND))
				.toDto();
	}
}
