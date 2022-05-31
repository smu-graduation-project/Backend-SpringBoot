package com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.auth.PrincipalDetails;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 권한이나 인증이 필요한 특정 주소를 요청했을 때 BasicAuthenticationFilter를 타게 된다.
// 권한이나 인증이 필요하지 않다면 거치지 않는다.
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private JwtService jwtService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}

	// 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 거친다.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("CHECK JWT : JwtAuthorizationFilter.doFilterInternal");

		// 1. 권한이나 인증이 필요한 요청이 전달됨
		// 2. Header 확인
		if (!jwtService.isValidHeader(request)) {
			chain.doFilter(request, response);
			return;
		}

		// 3. JWT 토큰을 검증해서 정상적인 사용자인지, 권한이 맞는지 확인
		String accessJwtToken = request
				.getHeader(JwtProperties.HEADER_PREFIX)
				.replace(JwtProperties.TOKEN_PREFIX, "");
		String refreshJwtToken = request
				.getHeader(JwtProperties.REFRESH_HEADER_PREFIX)
				.replace(JwtProperties.TOKEN_PREFIX, "");

		// RefreshToken 만료 또는 유효하지 않은 경우 인증 안함
		if (!jwtService.isValidToken(refreshJwtToken)) {
			chain.doFilter(request, response);
			return;
		}

		Member memberByRefreshToken = jwtService.getMemberByRefreshToken(refreshJwtToken);
		String username = memberByRefreshToken.getUsername();
		Long id = memberByRefreshToken.getSeq();

		// Access 유효 -> Access 토큰만 재발급
		if (jwtService.isValidToken(accessJwtToken)) {
			accessJwtToken = jwtService.createAccessToken(id, username);
			response.addHeader(JwtProperties.HEADER_PREFIX, JwtProperties.TOKEN_PREFIX + accessJwtToken);
		}
		// Access 만료 -> Access 토큰만 재발급
		else if (jwtService.isExpiredToken(accessJwtToken)) {
			accessJwtToken = jwtService.createAccessToken(id, username);
			response.addHeader(JwtProperties.HEADER_PREFIX, JwtProperties.TOKEN_PREFIX + accessJwtToken);
		} else {
			chain.doFilter(request, response);
			return;
		}

		// 4. 정상적인 서명이 검증되었으므로 조회 된 회원을 PricipalDetails 객체로 감싼다.
		PrincipalDetails principalDetails = new PrincipalDetails(memberByRefreshToken);

		// 5. jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				principalDetails, null, principalDetails.getAuthorities());

		// 6. 강제로 시큐리티_세션에 접근하여 Authentication 객체를 저장해준다.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
