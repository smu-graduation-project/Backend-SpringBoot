package com.graduatioinProject.sensorMonitoring.rawData.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataRequest;
import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataResponse;
import com.graduatioinProject.sensorMonitoring.rawData.service.RawDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Api(tags = "07. RowData")
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/realtime")
public class RawDataController {
	private final RawDataService rawDataService;
	private final ResponseService responseService;

	@PostMapping("/rawData")
	@ApiOperation(value = "로우 데이터 저장(프론트 처리 X)", notes = "데이터 서버로 데이터를 받아서 저장합니다.")
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

	@GetMapping("/rawData")
	@ApiOperation(value = "로우 데이터 호출", notes = "로우 데이터를 반환합니다.")
	public ListResult<RawDataResponse> getRawDataDtoList(
			@ApiParam(value = "노드 번호", required = true) @RequestParam Long nodePort,
			@ApiParam(value = "기간(시간 단위)") @RequestParam int hour
	) {
		try {
			List<RawDataResponse> data = rawDataService.findRawDataList(nodePort, hour);
			return responseService.listResult(data);
		} catch (Exception e) {
			throw new BussinessException("로우 데이터 조회에 실패하였습니다." + e.getMessage());
		}
	}
}
