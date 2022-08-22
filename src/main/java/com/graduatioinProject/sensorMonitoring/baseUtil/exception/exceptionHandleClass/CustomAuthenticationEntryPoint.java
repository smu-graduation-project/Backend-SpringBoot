package com.graduatioinProject.sensorMonitoring.baseUtil.exception.exceptionHandleClass;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

		String exceptionMessage = (String) request.getAttribute(JwtProperties.EXCEPTION);

		log.error("Exception : " + exceptionMessage);

		setResponse(response, exceptionMessage);
	}

	private void setResponse(HttpServletResponse response, String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=UTF-8");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", false);
		jsonObject.put("code", -1);
		jsonObject.put("message", message);

		response.getWriter().print(jsonObject);
	}
}
