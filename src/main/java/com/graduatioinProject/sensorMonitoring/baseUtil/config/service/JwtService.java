package com.graduatioinProject.sensorMonitoring.baseUtil.config.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt.JwtProperties;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.JwtErrorCode;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.CustomJwtException;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.member.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
@Getter
@RequiredArgsConstructor
public class JwtService {

	@Value("${jwt.secret}")
	private String SECRET_KEY;
	private final MemberRepository memberRepository;

//	@Transactional(readOnly = true)
//	public Member getMember(String username) {
//		return memberRepository.findByUsername(username)
//				.orElseThrow(() -> new BussinessException(ExMessage.MEMBER_ERROR_NOT_FOUND));
//	}

	@Transactional(readOnly = true)
	public Member getMemberByRefreshToken(String token) {
		return memberRepository.findByRefreshToken(token)
				.orElseThrow(() -> new CustomJwtException(JwtErrorCode.JWT_REFRESH_EXPIRED.getCode()));
	}

	@Transactional
	public void setRefreshToken(String username, String refreshJwt) {
		memberRepository.findByUsername(username)
				.ifPresent(member -> member.setRefreshToken(refreshJwt));
	}

	@Transactional
	public void removeRefreshToken(String token) {
		memberRepository.findByRefreshToken(token)
				.ifPresent(m -> m.setRefreshToken(null));
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

	public void checkHeaderValid(HttpServletRequest request) {
		String accessJwt = request.getHeader(JwtProperties.HEADER_PREFIX);
		String refreshJwt = request.getHeader(JwtProperties.REFRESH_HEADER_PREFIX);

		if (accessJwt == null || refreshJwt == null) {
			throw new CustomJwtException(JwtErrorCode.JWT_ACCESS_NOT_VALID.getCode());
		}
	}

	public void checkTokenValid(String token) {
		JWT.require(Algorithm.HMAC512(SECRET_KEY))
				.build()
				.verify(token);
	}

	public boolean isExpiredToken(String token) {
		try {
			JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token);
		} catch (TokenExpiredException e) {
			log.info("만료 토큰");
			return true;
		}
		return false;
	}

	public boolean isNeedToUpdateRefreshToken(String token) {
		try {
			Date expiresAt = JWT.require(Algorithm.HMAC512(SECRET_KEY))
					.build()
					.verify(token)
					.getExpiresAt();

			Date current = new Date(System.currentTimeMillis());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(current);
			calendar.add(Calendar.DATE, 7);

			Date after7dayFromToday = calendar.getTime();

			// 7일 이내에 만료
			if (expiresAt.before(after7dayFromToday)) {
				log.info("리프레쉬 토큰 7일 이내 만료");
				return true;
			}
		} catch (TokenExpiredException e) {
			return true;
		}
		return false;
	}

	@Transactional
	public void logout(HttpServletRequest request) {
		try {
			checkHeaderValid(request);
			String refreshJwtToken = request
					.getHeader(JwtProperties.REFRESH_HEADER_PREFIX)
					.replace(JwtProperties.TOKEN_PREFIX, "");

			getMemberByRefreshToken(refreshJwtToken)
					.setRefreshToken(null);
		} catch (Exception e) {
			throw new CustomJwtException(JwtErrorCode.JWT_REFRESH_NOT_VALID.name());
		}
	}
}
