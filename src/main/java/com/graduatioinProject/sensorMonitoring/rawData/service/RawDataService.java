package com.graduatioinProject.sensorMonitoring.rawData.service;

import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataRequest;
import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataResponse;
import com.graduatioinProject.sensorMonitoring.rawData.entity.RawData;
import com.graduatioinProject.sensorMonitoring.rawData.repository.RawDataJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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
	public List<RawDataResponse> findRawDataList(Long nodePort, int hour) {
		return rawDataJpaRepository
				.findAllByNodePortAndTimeStampAfterOrderBySequence(nodePort, LocalDateTime.now().minusHours(hour))
				.stream()
				.map(RawData::toDto)
				.collect(Collectors.toList());
	}
}
