package com.graduatioinProject.sensorMonitoring.node.formerData.temperature;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final TemperatureRepository temperatureDataRepository;

    public Optional<Temperature> findTemperature(LocalDate date, Long nodePort) {
        return temperatureDataRepository.findByDateAndNodePort(date, nodePort);
    }

    public List<Temperature> findTemperatureList(LocalDate start, LocalDate end, Long nodePort) {
        return temperatureDataRepository.findAllByDateBetweenAndNodePort(start, end, nodePort);
    }
}