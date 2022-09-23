package com.graduatioinProject.sensorMonitoring.rawData.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataRequest;
import com.graduatioinProject.sensorMonitoring.rawData.service.RawDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RawDataController {
	private final RawDataService rawDataService;
	private final ResponseService responseService;

	@PostMapping("/rawData")
	@ApiOperation(value = "로우 데이터 저장", notes = "데이터 서버로 데이터를 받아서 저장합니다.")
	public CommonResult saveRawData(
			@ApiParam(value = "로우 데이터 객체", required = true)
			@ModelAttribute RawDataRequest rawDataRequest
			) {
		try {
			rawDataService.saveRawData(rawDataRequest);
			return responseService.successResult();
		} catch (Exception e) {
			return responseService.failResult(
					e.getMessage()
			);
		}
	}
}
