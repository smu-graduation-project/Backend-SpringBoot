package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.repository;

import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.entity.ElectricCurrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ElectricCurrentRepository extends JpaRepository<ElectricCurrent, Long> {
    List<FormerData> findAllByDateBetweenAndPort(LocalDate start, LocalDate end, Long port);
}
