package com.graduatioinProject.sensorMonitoring.formerData.temperature.repository;

import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    List<FormerData> findAllByDateBetweenAndPort(LocalDate start, LocalDate end, Long port);
}
