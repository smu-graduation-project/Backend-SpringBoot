package com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.auth.PrincipalDetails;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.dto.LoginReq;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.member.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/*
 스프링 시큐리티에서 제공하는 UsernamePasswordAuthenticationFilter
 POST login으로 username, password 요청 시 UsernamePasswordAuthenticationFilter가 동작
*/

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	// login 요청 시 로그인을 위해 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		log.info("로그인 시도 : JwtAuthenticationFilter.attemptAuthentication");
		ObjectMapper om = new ObjectMapper();
		try {
			// 1. username, password 받는다 -> 임시 authenticationToken 발급
			LoginReq login = om.readValue(request.getInputStream(), LoginReq.class);
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
			// 2. 정상인지 로그인 시도를 해본다.
			// authenticationToken을 넘기면 PrincipalDetailsService.class -> loadUserByUsername() 실행, password를 검증한다.
			return authenticationManager.authenticate(authenticationToken);
			// 3. 로그인이 되었다.
			// 4. authentication을 반환해준다.
			// authentication 객체를 반환하여 session에 저장. 이를 통해 편리하게 권한관리를 할 수 있다.
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// attemptAuthentication() 실행 후 인증이 정상적으로 완료되면 실행된다.
	// JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해준다.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		log.info("인증 완료 : JwtAuthenticationFilter.successfulAuthentication");

		PrincipalDetails principal = (PrincipalDetails) authResult.getPrincipal();
		Member member = principal.getMember();
		String accessJwt = jwtService.createAccessToken(member.getSeq(), member.getUsername());
		String refreshJwt = jwtService.createRefreshToken();

		// login 성공 -> Refresh 토큰 재발급
		jwtService.setRefreshToken(member.getUsername(), refreshJwt);

		response.addHeader(JwtProperties.HEADER_PREFIX, JwtProperties.TOKEN_PREFIX + accessJwt); // accessJwt 응답 헤더에 추가
		response.addHeader(JwtProperties.REFRESH_HEADER_PREFIX, JwtProperties.TOKEN_PREFIX + refreshJwt); // refreshJwt 응답 헤더에 추가

		response.sendRedirect("/api/v1/login/success");
		log.info("===============================인증 프로세스 완료========================================");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		String failReason = failed.getMessage().equals("CannotFoundMember") ? "CannotFoundMember" : "WrongPassword";

		log.error("FAIL : " + failReason);
		response.setHeader("reason", failReason);
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, failReason);
		log.info("===============================인증 프로세스 완료========================================");
	}
}
