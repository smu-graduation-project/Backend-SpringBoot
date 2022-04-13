package com.graduatioinProject.sensorMonitoring.formerData.voltage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoltageService {
    private final VoltageRepository voltageRepository;

    public List<Voltage> findVoltageList(LocalDate start, LocalDate end, Long nodePort) {
        return voltageRepository.findAllByDateBetweenAndNodePort(start, end, nodePort);
    }
}