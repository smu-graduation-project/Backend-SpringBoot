package com.graduatioinProject.sensorMonitoring.formerData.voltage.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;

import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.entity.Temperature;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.service.VoltageService;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.entity.Voltage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/voltage")
public class VoltageController {

    private final VoltageService voltageService;
    private final ResponseService responseService;

    @GetMapping("/list")
    public CommonResult getVoltageList(FormerDataRequest request) {
        LocalDate startDate = LocalDate.parse(request.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), DateTimeFormatter.ISO_DATE);

        List<Voltage> voltageList = voltageService
                .findVoltageList(startDate, endDate, request.getPort());

        List<FormerDataResponse> result = new ArrayList<>();
        voltageList.stream().forEach(i -> result.add(i.toResponse()));

        if(result.isEmpty()) {
            return responseService.failResult("해당 데이터가 존재하지 않습니다.");
        }

ㅁㅂㅂ        try {
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
