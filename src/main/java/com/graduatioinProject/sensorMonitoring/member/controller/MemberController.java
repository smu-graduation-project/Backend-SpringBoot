package com.graduatioinProject.sensorMonitoring.member.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.member.config.MemberProperties;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberRes;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberSignupReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "01. 회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/member")
public class MemberController {

    private final ResponseService responseService;
    private final MemberService memberService;

    @ApiOperation(value = "회원 저장", notes = "아이디, 패스워드를 받아서 저장합니다.")
    @PostMapping
    public CommonResult saveUserMember(
            @ApiParam(value = "회원 객체", required = true)
            @ModelAttribute MemberSignupReq memberSignupReq
    ) {
        try {
            memberService.signUp(memberSignupReq, MemberProperties.USER);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @ApiOperation(value = "ADMIN 회원 저장", notes = "관리자 회원의 아이디, 패스워드를 받아서 저장합니다.")
    @PostMapping("/admin")
    public CommonResult saveAdminMember(
            @ApiParam(value = "Admin 회원 객체", required = true)
            @ModelAttribute MemberSignupReq memberSignupReq
    ) {
        try {
            memberService.signUp(memberSignupReq, MemberProperties.ADMIN);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @ApiOperation(value = "아이디로 회원 조회", notes = "아이디로 회원을 조회합니다.")
    @GetMapping("/username")
    public SingleResult<MemberRes> findMemberByEmail(
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String username
    ) {
        MemberRes byUserId = memberService.findByUsername(username);
        return responseService.singleResult(byUserId);
    }

    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원을 조회합니다.")
    @GetMapping("/all")
    public ListResult<MemberRes> findAllMember() {
        List<MemberRes> allByName = memberService.findAll();
        return responseService.listResult(allByName);
    }

    @PostMapping("/signUp")
    @ApiOperation(value = "회원가입", notes = "아이디, 비밀번호를 이용하여 USER 회원가입")
    public CommonResult memberSignUp(
            @ApiParam(required = true) @RequestBody MemberSignupReq memberSignupReq
    ) {
        try {
            memberService.signUp(memberSignupReq, MemberProperties.USER);
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
            memberService.signIn(userId, password);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
