package com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.auth.PrincipalDetails;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.JwtErrorCode;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.CustomJwtException;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
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
	private final JwtService jwtService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}

	// 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 거친다.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("CHECK JWT : 인가(권한) 검증 _ JwtAuthorizationFilter.doFilterInternal");

		try {
			jwtService.checkHeaderValid(request);
			String accessJwtToken = request
					.getHeader(JwtProperties.HEADER_PREFIX)
					.replace(JwtProperties.TOKEN_PREFIX, "");
			String refreshJwtToken = request
					.getHeader(JwtProperties.REFRESH_HEADER_PREFIX)
					.replace(JwtProperties.TOKEN_PREFIX, "");
			jwtService.checkTokenValid(refreshJwtToken);

			log.info("리프레쉬 토큰 회원 조회");
			Member memberByRefreshToken = jwtService.getMemberByRefreshToken(refreshJwtToken);
			String username = memberByRefreshToken.getUsername();
			Long id = memberByRefreshToken.getSeq();

			// Refresh 토큰이 7일 이내 만료일 경우 Refresh 토큰도 재발급
			if (jwtService.isNeedToUpdateRefreshToken(refreshJwtToken)) {
				refreshJwtToken = jwtService.createRefreshToken();
				response.addHeader(JwtProperties.REFRESH_HEADER_PREFIX, JwtProperties.TOKEN_PREFIX + refreshJwtToken);
				jwtService.setRefreshToken(username, refreshJwtToken);
			}

			try {
				log.info("액세스 토큰 검증");
				jwtService.checkTokenValid(accessJwtToken);
			} catch (TokenExpiredException expired) {
				log.error("ACCESS TOKEN REISSUE : " + JwtErrorCode.JWT_ACCESS_EXPIRED);
				accessJwtToken = jwtService.createAccessToken(id, username);
				response.addHeader(JwtProperties.HEADER_PREFIX, JwtProperties.TOKEN_PREFIX + accessJwtToken);
			}

			PrincipalDetails principalDetails = new PrincipalDetails(memberByRefreshToken);
			Authentication auth = new UsernamePasswordAuthenticationToken
					(principalDetails, null, principalDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (CustomJwtException cusJwtExc) {
			request.setAttribute(JwtProperties.EXCEPTION, cusJwtExc.getMessage());
		} catch (TokenExpiredException ee) {
			request.setAttribute(JwtProperties.EXCEPTION, JwtErrorCode.JWT_REFRESH_EXPIRED);
		} catch (MalformedJwtException | UnsupportedJwtException mj) {
			request.setAttribute(JwtProperties.EXCEPTION, JwtErrorCode.JWT_NOT_VALID);
		} catch (Exception e) {
			log.error("미정의 에러 : " + e);
			log.error(e.getMessage());
			request.setAttribute(JwtProperties.EXCEPTION, JwtErrorCode.JWT_NOT_VALID);
		}

		chain.doFilter(request, response);
	}
}
