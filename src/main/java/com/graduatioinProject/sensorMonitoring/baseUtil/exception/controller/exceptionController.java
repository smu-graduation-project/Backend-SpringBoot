package com.graduatioinProject.sensorMonitoring.baseUtil.exception.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.AccessDeniedException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/exception")
public class exceptionController {

	@GetMapping("/entrypoint")
	public void entrypointException() {
		throw new BussinessException(ExMessage.JWT_ERROR_FORMAT);
	}

	@GetMapping("/accessDenied")
	public void accessDeniedException(HttpServletResponse response) {
		throw new AccessDeniedException(ExMessage.JWT_ACCESS_DENIED);
	}
}
