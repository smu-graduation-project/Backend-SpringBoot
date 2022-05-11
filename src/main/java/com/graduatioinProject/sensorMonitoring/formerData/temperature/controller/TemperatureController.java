package com.graduatioinProject.sensorMonitoring.formerData.temperature.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.service.TemperatureService;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.entity.Temperature;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;
    private final ResponseService responseService;

    @GetMapping("/list")
    public CommonResult getTemperatureList(FormerDataRequest request) {
        /**
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.
         * User - realtionship - Site 를 구성하고,
         * 해당 nodePort가 속한 Site와 비교하여 찾으면 될 듯.
         */

        LocalDate startDate = LocalDate.parse(request.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), DateTimeFormatter.ISO_DATE);

        List<Temperature> temperatureList = temperatureService
                .findTemperatureList(startDate, endDate, request.getPort());

        List<FormerDataResponse> result = new ArrayList<>();
        temperatureList.stream().forEach(i -> result.add(i.toResponse()));

        if(result.isEmpty()) {
            return responseService.failResult("해당 데이터가 존재하지 않습니다.");
        }

        try {
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}