package com.graduatioinProject.sensorMonitoring.baseUtil.exception;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

	private final ResponseService responseService;

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public CommonResult undefinedExceptionHandler(HttpServletRequest request, Exception e) {
		log.error("\n\n미정의 에러");
		log.error(e.getMessage() + "\n\n");
		return responseService.failResult(e.getMessage());
	}

	@ExceptionHandler(BussinessException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public CommonResult bussinessExceptionHandler(HttpServletRequest request, Exception e) {
		log.error("\n\n" + e.getMessage() + "\n\n");
		return responseService.failResult(e.getMessage());
	}
}
