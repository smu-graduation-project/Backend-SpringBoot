package com.graduatioinProject.sensorMonitoring.AuthorizationTestPakage.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "9999. 권한 테스트")
@RestController
@RequestMapping("/v1/api/authorizationTest")
@RequiredArgsConstructor
public class AuthorizationTestController {

	private final ResponseService responseService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping
	@ApiOperation(value = "사이트 조회", notes ="USER/ADMIN 사용자 조회 가능")
	public SingleResult<String> getSite() {
		return responseService.singleResult("site view");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	@ApiOperation(value = "사이트 등록", notes ="ADMIN 사용자만 등록 가능")
	public SingleResult<String> registSite() {
		return responseService.singleResult("site regist");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping
	@ApiOperation(value = "사이트 삭제", notes ="ADMIN 사용자만 삭제 가능")
	public SingleResult<String> deleteSite() {
		return responseService.singleResult("site delete");
	}

}
