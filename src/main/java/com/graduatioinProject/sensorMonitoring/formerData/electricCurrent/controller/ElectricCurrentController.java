package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;

import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.entity.ElectricCurrent;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.service.ElectricCurrentService;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/electricCurrent")
public class ElectricCurrentController {

    private final ElectricCurrentService electricCurrentService;
    private final ResponseService responseService;

    @GetMapping("/list")
    public CommonResult getElectricCurrentList(FormerDataRequest request) {
        LocalDate startDate = LocalDate.parse(request.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), DateTimeFormatter.ISO_DATE);

        List<ElectricCurrent> electricCurrentList  = electricCurrentService
                .findElectricCurrentList(startDate, endDate, request.getPort());

        List<FormerDataResponse> result = new ArrayList<>();
        electricCurrentList.stream().forEach(i -> result.add(i.toResponse()));

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
