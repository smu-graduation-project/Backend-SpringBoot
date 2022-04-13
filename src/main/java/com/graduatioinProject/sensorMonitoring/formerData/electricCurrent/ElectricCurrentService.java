package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectricCurrentService {
    private final ElectricCurrentRepository ElectricCurrentRepository;

    public List<ElectricCurrent> findElectricCurrentList(LocalDate start, LocalDate end, Long nodePort) {
        return ElectricCurrentRepository.findAllByDateBetweenAndNodePort(start, end, nodePort);
    }
}