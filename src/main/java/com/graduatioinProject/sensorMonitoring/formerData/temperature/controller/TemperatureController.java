package com.graduatioinProject.sensorMonitoring.formerData.temperature.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.service.TemperatureService;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "05. 이전 데이터(온도)")
@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;
    private final ResponseService responseService;

    @GetMapping("/list/{port}")
    @ApiOperation(value = "온도 이전 데이터 목록", notes = "날짜와 port를 받아 온도 이전 데이러 목록을 반환")
    public ListResult<FormerDataResponse> getTemperatureList(@PathVariable Long port, FormerDataRequest request) {

        List<FormerDataResponse> result = temperatureService.findTemperatureList(request.getStartDate(), request.getEndDate(), port);
        if(result.isEmpty()) {
            throw new BussinessException(ExMessage.DATA_ERROR_NOT_FOUND.getMessage());
        }
        return responseService.listResult(result);
    }
}