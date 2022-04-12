package com.graduatioinProject.sensorMonitoring.node.formerData.temperature;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/node/formerData")
public class TemperatureController {

    private final TemperatureService temperatureDataService;
    private final ResponseService responseService;

    @GetMapping("/temperatureList")
    public ListResult<Temperature> getTemperatureList(@RequestBody HashMap<String, Object> body) {
        LocalDate start = LocalDate.parse((String) body.get("startDate"), DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse((String) body.get("endDate"), DateTimeFormatter.ISO_DATE);
        Long nodePort = Long.valueOf((String) body.get("nodePort"));

        /**
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.(Site 단위로 체크 해도 괜찮지 않을까??)
         */

        try {
            return  responseService.listResult(
                    temperatureDataService.findTemperatureList(start,end, nodePort));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

//    @GetMapping("/temperature")
//    public SingleResult<Temperature> getTemperature(@RequestBody HashMap<String, Object> body) {
//        LocalDate date = LocalDate.parse((String) body.get("date"), DateTimeFormatter.ISO_DATE);
//        int nodePort = Integer.parseInt((String) body.get("port"));
//        try {
//            return responseService.singleResult(
//                    temperatureDataService.findTemperature(date, nodePort).get());
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BussinessException(e.getMessage());
//        }
//    }
}
