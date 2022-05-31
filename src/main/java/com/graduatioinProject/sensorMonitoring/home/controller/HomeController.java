package com.graduatioinProject.sensorMonitoring.home.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.member.dto.LoginReq;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberSignupReq;
import com.graduatioinProject.sensorMonitoring.member.dto.Role;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = "0. 홈")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeController {

	private final MemberService memberService;
	private final ResponseService responseService;

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "이메일, 비밀번호를 이용하여 로그인")
	public void login(
			@ApiParam(value = "로그인 객체", required = true) @RequestBody LoginReq loginReq
	) {
	}

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입", notes = "아이디, 비밀번호를 이용하여 회원가입")
	public CommonResult memberSignUp(
			@ApiParam(required = true) @RequestBody MemberSignupReq memberSignupReq
	) {
		try {
			memberService.signUp(memberSignupReq, Role.USER);
			return responseService.successResult();
		} catch (Exception e) {
			return responseService.failResult(
					e.getMessage()
			);
		}
	}

	@ApiIgnore
	@GetMapping("/login/success")
	public CommonResult successLogin() {
		return responseService.successResult();
	}
}
