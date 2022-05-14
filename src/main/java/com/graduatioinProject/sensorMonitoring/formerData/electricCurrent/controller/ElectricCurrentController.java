package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.service.ElectricCurrentService;

import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api(tags = "03. 이전 데이터(전류)")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/electricCurrent")
public class ElectricCurrentController {

    private final ElectricCurrentService electricCurrentService;
    private final ResponseService responseService;

    @GetMapping("/list/{port}")
    @ApiOperation(value = "전류 이전 데이터 목록", notes = "날짜와 port를 받아 전류 이전 데이러 목록을 반환")
    public CommonResult getElectricCurrentList(@PathVariable Long port,
                                               HttpServletRequest httpServletRequest,
                                               FormerDataRequest request) {

        HttpSession session = httpServletRequest.getSession(false);

        if (session == null) {
            return responseService.failResult(ExMessage.SESSION_ERROR_NOT_EXIST.getMessage());
        }
        MemberSessionDto loginMember = (MemberSessionDto) session.getAttribute("member");

        // 세션에 해당 회원의 데이터가 있는지
        if (loginMember == null) {
            return responseService.failResult(ExMessage.SESSION_ERROR_MEMBER_NOT_FOUND.getMessage());
        }

        List<FormerDataResponse> result = electricCurrentService.findElectricCurrentList(request.getStartDate(), request.getEndDate(), port);
        return responseService.listResult(result);
    }
}
