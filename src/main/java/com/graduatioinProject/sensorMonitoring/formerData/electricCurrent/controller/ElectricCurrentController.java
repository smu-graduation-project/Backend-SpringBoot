package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.SessionService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.service.ElectricCurrentService;

import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "03. 이전 데이터(전류)")
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/electricCurrent")
public class ElectricCurrentController {

    private final ElectricCurrentService electricCurrentService;
    private final SessionService sessionService;
    private final ResponseService responseService;

    @GetMapping("/list/{port}")
    @ApiOperation(value = "전류 이전 데이터 목록", notes = "날짜와 port를 받아 전류 이전 데이러 목록을 반환")
    public CommonResult getElectricCurrentList(@PathVariable Long port,
                                               HttpServletRequest httpServletRequest,
                                               FormerDataRequest request) {

        MemberSessionDto loginMember = sessionService.checkMemberSession(httpServletRequest);

        try {
            List<FormerDataResponse> result = electricCurrentService.findElectricCurrentList(request.getStartDate(), request.getEndDate(), port);
            return  responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
