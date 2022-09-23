package com.graduatioinProject.sensorMonitoring.rawData.entity;

import com.graduatioinProject.sensorMonitoring.rawData.dto.RawDataResponse;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Api(tags = "로우 데이터")
@Slf4j
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RawData {
	@Id @Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	@Column private Long nodePort;
	@Column private LocalDateTime timeStamp;
	@Column private Integer sequence;
	@Column private Double temperature;
	@Column private Double voltage;
	@Column private String electricCurrent;

	public RawDataResponse toDto() {
		return RawDataResponse.builder()
				.nodePort(nodePort)
				.timeStamp(timeStamp.toString())
				.sequence(sequence)
				.temperature(temperature)
				.voltage(voltage)
				.electricCurrent(electricCurrent)
				.build();
	}
}
