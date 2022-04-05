package com.graduatioinProject.sensorMonitoring.node.formerData.temperature;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/formerData")
public class TemperatureDataController {

    private final TemperatureDataService temperatureDataService;
    private final ResponseService responseService;

    public TemperatureDataController(TemperatureDataService formerDataService, ResponseService responseService) {
        this.temperatureDataService = formerDataService;
        this.responseService = responseService;
    }

    @GetMapping("/temperatureList")
    public ListResult<TemperatureData> getTemperatureList(@RequestBody HashMap<String, Object> body) {
        LocalDate start = LocalDate.parse((String) body.get("startDate"), DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse((String) body.get("endDate"), DateTimeFormatter.ISO_DATE);
        List<TemperatureData> temperatureDataArrayList = temperatureDataService.findTemperatures(start, end);

        if(temperatureDataArrayList.isEmpty()) {
            return (ListResult<TemperatureData>) responseService.failResult("해당 날짜에 대한 정보가 없습니다.");
        }
        return responseService.listResult(temperatureDataArrayList);
    }

    /**
     * Test
     */

//    @GetMapping("/temperatureList")
//    public ResponseEntity<ListResult<TemperatureData>> getTemperatureList(@RequestBody HashMap<String, Object> body) {
//        LocalDate start = LocalDate.parse((String) body.get("startDate"), DateTimeFormatter.ISO_DATE);
//        LocalDate end = LocalDate.parse((String) body.get("endDate"), DateTimeFormatter.ISO_DATE);
//        List<TemperatureData> temperatureDataArrayList = formerDataService.findTemperatures(start, end);
//
//        if(temperatureDataArrayList.isEmpty()) {
//            return ResponseEntity.ok((ListResult<TemperatureData>) responseService.failResult("해당 날짜에 대한 정보가 없습니다."));
//        }
//        return ResponseEntity.ok(responseService.listResult(temperatureDataArrayList));
//    }

    @GetMapping("/temperatureRaw")
    public List<TemperatureData> getTemperatureRaw(@RequestBody HashMap<String, Object> body) {
        LocalDate start = LocalDate.parse((String) body.get("startDate"), DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse((String) body.get("endDate"), DateTimeFormatter.ISO_DATE);
        List<TemperatureData> temperatureDataArrayList = temperatureDataService.findTemperatures(start, end);
        return temperatureDataArrayList;
    }

    @GetMapping("/temperature")
    public SingleResult<TemperatureData> getTemperature(@RequestBody HashMap<String, Object> body) {
        LocalDate date = LocalDate.parse((String) body.get("date"), DateTimeFormatter.ISO_DATE);
        Optional<TemperatureData> temperatureData = temperatureDataService.findTemperature(date);
        if(temperatureData.isEmpty()) {
            return (SingleResult<TemperatureData>) responseService.failResult("해당 날짜에 대한 정보가 없습니다.");
        }
        return responseService.singleResult(temperatureData.get());
    }
}
