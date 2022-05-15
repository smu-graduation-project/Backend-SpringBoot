package com.graduatioinProject.sensorMonitoring.member.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberReq;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberRes;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "01. 회원")
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/member")
public class MemberController {

    private final ResponseService responseService;
    private final MemberService memberService;

    @ApiOperation(value = "회원 저장", notes = "아이디, 패스워드를 받아서 저장합니다.")
    @PostMapping
    public SingleResult<MemberRes> saveMember(
            @ApiParam(value = "회원 객체", required = true)
            @ModelAttribute MemberReq member
    ) {
        return responseService.singleResult(memberService.save(member).toDto());
    }

    @ApiOperation(value = "아이디로 회원 조회", notes = "아이디로 회원을 조회합니다.")
    @GetMapping("/email")
    public SingleResult<MemberRes> findMemberByEmail(
            @ApiParam(value = "회원 이메일", required = true) @RequestParam String userId
    ) {
        MemberRes byUserId = memberService.findByUserId(userId);
        return responseService.singleResult(byUserId);
    }

    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원을 조회합니다.")
    @GetMapping("/all")
    public ListResult<MemberRes> findAllMember() {
        List<MemberRes> allByName = memberService.findAll();
        return responseService.listResult(allByName);
    }
}
