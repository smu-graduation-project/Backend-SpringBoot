package com.graduatioinProject.sensorMonitoring.rawData.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponseWithAll;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepository;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataRequest;
import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataResponse;
import com.graduatioinProject.sensorMonitoring.rawData.entity.RawData;
import com.graduatioinProject.sensorMonitoring.rawData.repository.RawDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RawDataService {

	private final RawDataJpaRepository rawDataJpaRepository;

	@Transactional
	public void saveRawData(RawDataRequest rawDataRequest) {
		rawDataJpaRepository.save(
				RawData.builder()
						.nodePort(rawDataRequest.getNodePort())
						.timeStamp(rawDataRequest.extractLocalDateTime())
						.sequence(rawDataRequest.getSequence())
						.temperature(rawDataRequest.getTemperature())
						.voltage(rawDataRequest.getVoltage())
						.electricCurrent(rawDataRequest.getElectricCurrent())
						.build()
		);
	}

	@Transactional(readOnly = true)
	public List<RawDataResponse> findRawDataList(int nodePort, LocalDate startDate, LocalDate endDate) {
		return rawDataJpaRepository
				.findRawDataByNodePortAndTimeStampBetweenOOrderByTimeStamp(nodePort, startDate, endDate)
				.stream()
				.map(RawData::toDto)
				.collect(Collectors.toList());
	}
}
