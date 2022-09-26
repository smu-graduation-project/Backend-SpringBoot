package com.graduatioinProject.sensorMonitoring.home.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheck;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.member.dto.LoginReq;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberSignupReq;
import com.graduatioinProject.sensorMonitoring.member.dto.Role;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@CrossOrigin
@Api(tags = "0. 홈")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeController {

	@Value("${jwt.secret}")
	private String SECRET_KEY;
	private final JwtService jwtService;
	private final MemberService memberService;
	private final ResponseService responseService;

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "이메일, 비밀번호를 이용하여 로그인")
	public void login(
			@ApiParam(value = "로그인 객체", required = true) @RequestBody LoginReq loginReq
	) {
	}

	@PostMapping("/logout")
	@ApiOperation(value = "로그아웃", notes = "AccessToken & RefreshToken 헤더에 담아서 로그아웃 요청")
	public CommonResult logout(HttpServletRequest request) {

		try {
			jwtService.logout(request);
			return responseService.successResult();
		} catch (Exception e) {
			return responseService.failResult(e.getMessage());
		}
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

	@LoginCheck
	@ApiOperation(value = "회원 등급 조회", notes = "회원 등급을 반환합니다.")
	@GetMapping("/role")
	public CommonResult findMemberRole(HttpServletRequest httpServletRequest) {
		String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
		return responseService.singleResult(memberService.findByUsername(userName).getRole());
	}
}
