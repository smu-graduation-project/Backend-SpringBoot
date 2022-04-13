package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/formerData/electricCurrent")
public class ElectricCurrentController {

    private final ElectricCurrentService electricCurrentService;
    private final ResponseService responseService;

    @GetMapping("/list")
    public ListResult<ElectricCurrent> getElectricCurrentList(@RequestBody HashMap<String, Object> body) {
        LocalDate start = LocalDate.parse((String) body.get("startDate"), DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse((String) body.get("endDate"), DateTimeFormatter.ISO_DATE);
        Long nodePort = Long.valueOf((String) body.get("nodePort"));

        /**
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.
         * NodeDetailService에서 해당 nodePort에 대한 데이터를 가져오고,
         * 이 중 요청한 유저가 있는지 판별,
         */

        try {
            return  responseService.listResult(
                    electricCurrentService.findElectricCurrentList(start,end, nodePort));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
