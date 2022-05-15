package com.graduatioinProject.sensorMonitoring.formerData.voltage.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.SessionService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;

import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.service.VoltageService;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "04. 이전 데이터(전압)")
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/voltage")
public class VoltageController {

    private final VoltageService voltageService;
    private final SessionService sessionService;
    private final NodeService nodeService;
    private final ResponseService responseService;

    @GetMapping("/list/{port}")
    @ApiOperation(value = "전압 이전 데이터 목록", notes = "날짜와 port를 받아 전압 이전 데이러 목록을 반환")
    public CommonResult getVoltageList(@PathVariable Long port,
                                       HttpServletRequest httpServletRequest,
                                       FormerDataRequest request) {

        MemberSessionDto loginMember = sessionService.checkMemberSession(httpServletRequest);

//        // 해당 Port의 노드가 존재하는지
//        if (!nodeService.checkNodePort(port)) {
//            return responseService.failResult(ExMessage.NODE_ERROR_NOT_FOUND.getMessage());
//        }

        try {
            List<FormerDataResponse> result = voltageService.findVoltageList(request.getStartDate(), request.getEndDate(), port);
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
