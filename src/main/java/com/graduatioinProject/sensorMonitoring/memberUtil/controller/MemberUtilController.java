package com.graduatioinProject.sensorMonitoring.memberUtil.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSignupReq;
import com.graduatioinProject.sensorMonitoring.memberUtil.service.MemberUtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "02. 회원가입")
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class MemberUtilController {

	private final MemberUtilService memberUtilService;
	private final ResponseService responseService;

	@PostMapping("/signUp")
	@ApiOperation(value = "회원가입", notes = "아이디, 비밀번호를 이용하여 회원가입")
	public CommonResult memberSignUp(
			@ApiParam(required = true) @RequestBody MemberSignupReq memberSignupReq
	) {
		try {
			memberUtilService.signUp(memberSignupReq);
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
