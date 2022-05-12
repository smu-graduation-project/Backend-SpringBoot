package com.graduatioinProject.sensorMonitoring.formerData.temperature.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.service.TemperatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "05. 이전 데이터(온도)")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;
    private final ResponseService responseService;

    @GetMapping("/list/{port}")
    @ApiOperation(value = "온도 이전 데이터 목록", notes = "날짜와 port를 받아 온도 이전 데이러 목록을 반환")
    public CommonResult getTemperatureList(@PathVariable Long port, FormerDataRequest request) {
        /**
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.
         * User - realtionship - Site 를 구성하고,
         * 해당 nodePort가 속한 Site와 비교하여 찾으면 될 듯.
         */

        List<FormerDataResponse> result = temperatureService.findTemperatureList(request.getStartDate(), request.getEndDate(), port);

        try {
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}