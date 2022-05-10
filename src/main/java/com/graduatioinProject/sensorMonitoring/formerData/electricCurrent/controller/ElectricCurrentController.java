package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.service.ElectricCurrentService;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.entity.ElectricCurrent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/electricCurrent")
public class ElectricCurrentController {

    private final ElectricCurrentService electricCurrentService;
    private final ResponseService responseService;

    @GetMapping("/list")
    public ListResult<ElectricCurrent> getElectricCurrentList(@RequestBody FormerDataRequest request) {

        try {
            return  responseService.listResult(
                    electricCurrentService.findElectricCurrentList(request.getStartDate(),request.getEndDate(), request.getNodePort()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
