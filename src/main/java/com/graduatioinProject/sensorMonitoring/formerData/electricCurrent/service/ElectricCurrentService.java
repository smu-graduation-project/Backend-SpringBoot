package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.service;

import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.entity.ElectricCurrent;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.repository.ElectricCurrentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElectricCurrentService {
    private final ElectricCurrentRepository electricCurrentRepository;

    public List<ElectricCurrent> findElectricCurrentList(LocalDate start, LocalDate end, Long nodePort) {
        // log.info("Get Electric Current List : " + LocalDateTime.now());
        return electricCurrentRepository.findAllByDateBetweenAndNodePort(start, end, nodePort);
    }
}