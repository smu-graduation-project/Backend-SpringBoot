package com.graduatioinProject.sensorMonitoring.formerData.temperature.service;

import com.graduatioinProject.sensorMonitoring.formerData.temperature.entity.Temperature;
import com.graduatioinProject.sensorMonitoring.formerData.temperature.repository.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final TemperatureRepository temperatureDataRepository;
    public List<Temperature> findTemperatureList(LocalDate start, LocalDate end, Long port) {
        return temperatureDataRepository.findAllByDateBetweenAndPort(start, end, port);
    }
}