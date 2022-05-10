package com.graduatioinProject.sensorMonitoring.memberUtil.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.DateConfig;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.member.repository.MemberRepository;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberUtilService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final HttpSession session;

	@Transactional
	public void signUp(String userId, String password1, String password2) {
		if (memberRepository.findByUserId(userId).isPresent()) {
			throw new BussinessException(ExMessage.MEMBER_ERROR_DUPLICATE);
		}

		verifyUserInfo(userId, password1, password2);

		if (memberRepository.findByUserId(userId).isPresent()) {
			throw new BussinessException(ExMessage.MEMBER_ERROR_DUPLICATE);
		} else {
			try {
				Member member = Member.builder()
						.userId(userId)
						.password(passwordEncoder.encode(password1))
						.signupType("E")
						.registDate(new DateConfig().getNowDate())
						.updateDate(new DateConfig().getNowDate())
						.activateYn("Y")
						.build();
				memberRepository.save(member);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BussinessException("회원가입에 실패하였습니다.");
			}
		}
	}

	@Transactional
	public void signIn(String userId, String password) {

		Member member = memberRepository
				.findByUserId(userId)
				.orElseThrow(() -> new BussinessException(ExMessage.MEMBER_ERROR_NOT_FOUND));

		if (!passwordEncoder.matches(password, member.getPassword())) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			throw new BussinessException(ExMessage.MEMBER_ERROR_PASSWORD);
		}
		// TODO : JWT or Session 발급 필요
		session.setAttribute("member", new MemberSessionDto(member));
	}

	private void verifyUserInfo(String userId, String password1, String password2) {
		verifyUserId(userId);
		verifyUserPassword(password1, password2);
	}

	private void verifyUserPassword(String password1, String password2) {
		Pattern passwordExpression = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
		if (!passwordExpression.matcher(password1).matches()) {
			throw new BussinessException("비밀번호는 영문자와 특수문자를 포함하여 8자 이상으로 이뤄져야 합니다.");
		} else if (!password1.equals(password2)) {
			throw new BussinessException("입력한 비밀번호가 서로 다름니다.");
		}
	}

	private void verifyUserId(String userId) {
		// 시작은 영문으로만, '_'를 제외한 특수문자 안되며 {영문, 숫자, '_'} 으로만 이루어진 5 ~ 12자 이하
		Pattern nameExpression = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
		if (!nameExpression.matcher(userId).matches()) {
			throw new BussinessException(ExMessage.MEMBER_ERROR_USER_ID_FORMAT);
		}
	}
}