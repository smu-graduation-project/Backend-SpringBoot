package com.graduatioinProject.sensorMonitoring.node.formerData.electricCurrent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ElectricCurrentRepository extends JpaRepository<ElectricCurrent, Long> {
    List<ElectricCurrent> findAllByDateBetweenAndNodePort(LocalDate start, LocalDate end, Long nodePort);
}
