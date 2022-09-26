package com.graduatioinProject.sensorMonitoring.rawData.repository;

import com.graduatioinProject.sensorMonitoring.rawData.entity.RawData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface RawDataJpaRepository extends JpaRepository<RawData, Long> {

    List<RawData> findAllByNodePortAndTimeStampAfterOrderByTimeStamp(Long nodePort, LocalDateTime timeStamp);
}
