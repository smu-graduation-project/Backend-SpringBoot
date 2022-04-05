package com.graduatioinProject.sensorMonitoring.node.formerData.temperature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TemperatureDataRepository extends JpaRepository<TemperatureData, Long> {
    List<TemperatureData> findAllByDateBetween(LocalDate start, LocalDate end);
    Optional<TemperatureData> findByDate(LocalDate date);
}
