package com.graduatioinProject.sensorMonitoring.rawData.repository;

import com.graduatioinProject.sensorMonitoring.rawData.entity.RawData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawDataJpaRepository extends JpaRepository<RawData, Long> {

}
