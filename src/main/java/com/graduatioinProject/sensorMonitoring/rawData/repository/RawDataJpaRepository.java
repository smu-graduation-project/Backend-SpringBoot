package com.graduatioinProject.sensorMonitoring.rawData.repository;

import com.graduatioinProject.sensorMonitoring.rawData.entity.RawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RawDataJpaRepository extends JpaRepository<RawData, Long> {

    List<RawData> findRawDataByNodePortAndTimeStampBetweenOOrderByTimeStamp(Integer nodePort, LocalDate start, LocalDate end);
}
