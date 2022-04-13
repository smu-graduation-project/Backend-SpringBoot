package com.graduatioinProject.sensorMonitoring.formerData.temperature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    List<Temperature> findAllByDateBetweenAndNodePort(LocalDate start, LocalDate end, Long nodePort);
    Optional<Temperature> findByDateAndNodePort(LocalDate date, Long nodePort);
}
