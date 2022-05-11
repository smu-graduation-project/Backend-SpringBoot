package com.graduatioinProject.sensorMonitoring.formerData.voltage.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;

import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.service.VoltageService;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.entity.Voltage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/voltage")
public class VoltageController {

    private final VoltageService voltageService;
    private final ResponseService responseService;

    @GetMapping("/list")
    public ListResult<FormerDataResponse> getVoltageList(@RequestBody FormerDataRequest request) {

        List<Voltage> voltageList = voltageService.findVoltageList(request.getStartDate(), request.getEndDate(), request.getPort());

        List<FormerDataResponse> result = new ArrayList<>();
        voltageList.stream().forEach(i -> result.add(i.toResponse()));

        try {
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
