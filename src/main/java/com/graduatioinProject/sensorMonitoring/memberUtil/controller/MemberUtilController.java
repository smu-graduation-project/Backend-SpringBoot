package com.graduatioinProject.sensorMonitoring.memberUtil.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.memberUtil.service.MemberUtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "02. 회원가입")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class MemberUtilController {

	private final MemberUtilService memberUtilService;
	private final ResponseService responseService;

	@PostMapping("/signUp")
	@ApiOperation(value = "회원가입", notes = "아이디, 비밀번호를 이용하여 회원가입")
	public CommonResult memberSignUp(
			@ApiParam(value = "회원 아이디", required = true) @RequestParam(name = "userId") String name,
			@ApiParam(value = "회원 패스워드1", required = true) @RequestParam(name = "password1") String password1,
			@ApiParam(value = "회원 패스워드2", required = true) @RequestParam(name = "password2") String password2
	) {
		try {
			memberUtilService.signUp(name, password1, password2);
			return responseService.successResult();
		} catch (Exception e) {
			return responseService.failResult(
					e.getMessage()
			);
		}
	}

	@PostMapping("/signIn")
	@ApiOperation(value = "로그인", notes = "이메일, 비밀번호를 이용하여 로그인")
	public CommonResult memberSignIn(
			@ApiParam(value = "회원 아이디", required = true) @RequestParam String userId,
			@ApiParam(value = "회원 패스워드", required = true) @RequestParam String password
	) {
		try {
			memberUtilService.signIn(userId, password);
			return responseService.successResult();
		} catch (Exception e) {
			return responseService.failResult(
					e.getMessage()
			);
		}
	}

}
