package com.graduatioinProject.sensorMonitoring.formerData.voltage.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;

import com.graduatioinProject.sensorMonitoring.formerData.voltage.service.VoltageService;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.entity.Voltage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/voltage")
public class VoltageController {

    private final VoltageService voltageService;
    private final ResponseService responseService;

    @GetMapping("/list")
    public ListResult<Voltage> getVoltageList(@RequestBody FormerDataRequest request) {

        try {
            return  responseService.listResult(
                    voltageService.findVoltageList(request.getStartDate(), request.getEndDate(), request.getNodePort()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
