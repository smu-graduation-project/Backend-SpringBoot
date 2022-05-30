package com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.auth.PrincipalDetails;
import com.graduatioinProject.sensorMonitoring.member.dto.LoginReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/*
 스프링 시큐리티에서 제공하는 UsernamePasswordAuthenticationFilter
 POST login으로 username, password 요청 시 UsernamePasswordAuthenticationFilter가 동작
*/

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final String SECRET_KEY;

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
			// authenticate()에 임시 생성한 authenticationToken을 넘기면
			// PrincipalDetailsService.class -> loadUserByUsername() 메소드 실행, password 일치를 검증한다.
			Authentication authentication = authenticationManager.authenticate(authenticationToken);

			// 3. 로그인이 되었다.
			// 인증되어 생성된 authentication에 있는 Principal 객체를 (PrincipalDetails) 객체로 꺼낸다.
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

			// 4. authentication을 반환해준다.
			// authentication 객체를 반환하여 session에 저장. 이를 통해 편리하게 권한관리를 할 수 있다.
			return authentication;
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
		String jwt = JWT.create()
				.withSubject("JWT_토큰")
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 만료시간 10m
				.withClaim("id", principal.getMember().getSeq())
				.withClaim("username", principal.getMember().getUsername())
				.sign(Algorithm.HMAC512(SECRET_KEY));

		response.addHeader(JwtProperties.HEADER_PREFIX, JwtProperties.TOKEN_PREFIX + jwt); // jwt 응답 헤더에 추가
	}
}
