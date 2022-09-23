package com.graduatioinProject.sensorMonitoring.rawData.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponseWithAll;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepository;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataRequest;
import com.graduatioinProject.sensorMonitoring.rawData.entity.RawData;
import com.graduatioinProject.sensorMonitoring.rawData.repository.RawDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RawDataService {

	private final RawDataJpaRepository rawDataJpaRepository;
	private final NodeRepository nodeRepository;

	@Transactional
	public void saveRawData(RawDataRequest rawDataRequest) {
		Long nodePort = rawDataRequest.getNodePort();
		NodeResponseWithAll nodeInfo = nodeRepository.findByPort(nodePort).toResponseWithAll();

		Long siteId = nodeInfo.getBatteryResponseWithSite().getSiteResponse().getId();
		Long batteryId = nodeInfo.getBatteryResponseWithSite().getId();

		rawDataJpaRepository.save(
				RawData.builder()
						.siteId(siteId)
						.batteryId(batteryId)
						.nodePort(rawDataRequest.getNodePort())
						.timeStamp(rawDataRequest.getTimeStamp())
						.sequence(rawDataRequest.getSequence())
						.temperature(rawDataRequest.getTemperature())
						.voltage(rawDataRequest.getVoltage())
						.electricCurrent(rawDataRequest.getElectricCurrent())
						.build()
		);
	}

	public void findRawDataList() {

	}
}
