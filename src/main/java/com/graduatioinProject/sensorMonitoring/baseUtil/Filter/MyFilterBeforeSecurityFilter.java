package com.graduatioinProject.sensorMonitoring.baseUtil.Filter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class MyFilterBeforeSecurityFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("Security Filter Start");
		chain.doFilter(request, response);
	}
}
