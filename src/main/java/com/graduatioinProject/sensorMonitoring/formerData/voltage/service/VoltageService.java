package com.graduatioinProject.sensorMonitoring.formerData.voltage.service;

import com.graduatioinProject.sensorMonitoring.formerData.voltage.entity.Voltage;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.repository.VoltageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoltageService {
    private final VoltageRepository voltageRepository;

    public List<Voltage> findVoltageList(LocalDate start, LocalDate end, Long port) {
        return voltageRepository.findAllByDateBetweenAndPort(start, end, port);
    }
}