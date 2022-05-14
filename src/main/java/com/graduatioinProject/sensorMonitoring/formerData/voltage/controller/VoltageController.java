package com.graduatioinProject.sensorMonitoring.formerData.voltage.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.service.VoltageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "04. 이전 데이터(전압)")
@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/voltage")
public class VoltageController {

    private final VoltageService voltageService;
    private final ResponseService responseService;

    @GetMapping("/list{port}")
    @ApiOperation(value = "전압 이전 데이터 목록", notes = "날짜와 port를 받아 전압 이전 데이러 목록을 반환")
    public CommonResult getVoltageList(@PathVariable Long port, FormerDataRequest request) {

        List<FormerDataResponse> result = voltageService.findVoltageList(request.getStartDate(), request.getEndDate(), port);
        if(result.isEmpty()) {
            return responseService.failResult(ExMessage.DATA_ERROR_NOT_FOUND.getMessage());
        }
        return responseService.listResult(result);
    }
}
