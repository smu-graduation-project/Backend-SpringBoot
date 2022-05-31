package com.graduatioinProject.sensorMonitoring.baseUtil.config.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt.JwtProperties;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.member.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Service
@Getter
@RequiredArgsConstructor
public class JwtService {

	@Value("${jwt.secret}")
	private String SECRET_KEY;
	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	public Member getMember(String username) {
		return memberRepository.findByUsername(username)
				.orElseThrow(() -> new BussinessException(ExMessage.MEMBER_ERROR_NOT_FOUND));
	}

	@Transactional(readOnly = true)
	public Member getMemberByRefreshToken(String token) {
		return memberRepository.findByRefreshToken(token)
				.orElseThrow(() -> new BussinessException(ExMessage.MEMBER_ERROR_NOT_FOUND));
	}

	@Transactional
	public void setRefreshToken(String username, String refreshJwt) {
		memberRepository.findByUsername(username)
				.ifPresent(member -> member.setRefreshToken(refreshJwt));
	}

	public String createAccessToken(Long id, String username) {
		return JWT.create()
				.withSubject(JwtProperties.ACCESS_TOKEN)
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 만료시간 10m
				.withClaim(JwtProperties.ID, id)
				.withClaim(JwtProperties.USERNAME, username)
				.sign(Algorithm.HMAC512(SECRET_KEY));
	}

	public String createRefreshToken() {
		return JWT.create()
				.withSubject(JwtProperties.REFRESH_TOKEN)
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESH_EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SECRET_KEY));
	}

	public boolean isValidHeader(HttpServletRequest request) {
		String accessJwt = request.getHeader(JwtProperties.HEADER_PREFIX);
		String refreshJwt = request.getHeader(JwtProperties.REFRESH_HEADER_PREFIX);

		if (accessJwt == null || refreshJwt == null) {
			return false;
		} else if (!accessJwt.startsWith(JwtProperties.TOKEN_PREFIX)
				|| !refreshJwt.startsWith(JwtProperties.TOKEN_PREFIX)) {
			return false;
		}

		return true;
	}

	public boolean isValidToken(String token) {
		try {
			JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isExpiredToken(String token) {
		try {
			JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token);
		} catch (TokenExpiredException e) {
			return true;
		}
		return false;
	}
}
