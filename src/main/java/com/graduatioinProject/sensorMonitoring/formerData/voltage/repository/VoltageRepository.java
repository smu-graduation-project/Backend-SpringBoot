package com.graduatioinProject.sensorMonitoring.formerData.voltage.repository;

import com.graduatioinProject.sensorMonitoring.formerData.voltage.entity.Voltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoltageRepository extends JpaRepository<Voltage, Long> {
    List<Voltage> findAllByDateBetweenAndPort(LocalDate start, LocalDate end, Long port);
}
