package com.graduatioinProject.sensorMonitoring.formerData.temperature.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.service.TemperatureService;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.entity.Temperature;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;
    private final ResponseService responseService;

    @GetMapping("/list")
    public ListResult<Temperature> getTemperatureList(@RequestBody FormerDataRequest request) {
        /**
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.
         * User - realtionship - Site 를 구성하고,
         * 해당 nodePort가 속한 Site와 비교하여 찾으면 될 듯.
         */

        try {
            return responseService.listResult(
                    temperatureService.findTemperatureList(request.getStartDate(), request.getEndDate(), request.getNodePort()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}