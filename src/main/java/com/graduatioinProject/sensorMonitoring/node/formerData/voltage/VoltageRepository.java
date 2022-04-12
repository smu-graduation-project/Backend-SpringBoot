package com.graduatioinProject.sensorMonitoring.node.formerData.voltage;

import com.graduatioinProject.sensorMonitoring.node.formerData.electricCurrent.ElectricCurrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoltageRepository extends JpaRepository<Voltage, Long> {
    List<Voltage> findAllByDateBetweenAndNodePort(LocalDate start, LocalDate end, Long nodePort);
}
