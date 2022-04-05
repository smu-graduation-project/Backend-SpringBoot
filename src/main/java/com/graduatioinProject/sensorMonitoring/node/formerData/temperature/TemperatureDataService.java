package com.graduatioinProject.sensorMonitoring.node.formerData.temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureDataService {
    @Autowired
    private final TemperatureDataRepository temperatureDataRepository;

    public TemperatureDataService(TemperatureDataRepository temperatureDataRepository) {
        this.temperatureDataRepository = temperatureDataRepository;
    }

    public Optional<TemperatureData> findTemperature(LocalDate date) {
        return temperatureDataRepository.findByDate(date);
    }

    public List<TemperatureData> findTemperatures(LocalDate start, LocalDate end) {
        return temperatureDataRepository.findAllByDateBetween(start, end);
    }
}