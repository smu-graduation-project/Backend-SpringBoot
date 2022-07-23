package com.graduatioinProject.sensorMonitoring.formerData.service;

import com.graduatioinProject.sensorMonitoring.formerData.Repository.ElectricCurrentRepository;
import com.graduatioinProject.sensorMonitoring.formerData.Repository.TemperatureRepository;
import com.graduatioinProject.sensorMonitoring.formerData.Repository.VoltageRepository;
import com.graduatioinProject.sensorMonitoring.formerData.entity.FormerData;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/15
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class FormerDataService {
    private final ElectricCurrentRepository electricCurrentRepository;
    private final TemperatureRepository temperatureRepository;
    private final VoltageRepository voltageRepository;

    @Transactional(readOnly = true)
    public List<FormerDataResponse> getTemperatureList(LocalDate start, LocalDate end, Long port) {
        return temperatureRepository.findAllByDateBetweenAndPort(start, end, port)
                .stream()
                .map(FormerData::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FormerDataResponse> getVoltageList(LocalDate start, LocalDate end, Long port) {
        return voltageRepository.findAllByDateBetweenAndPort(start, end, port)
                .stream()
                .map(FormerData::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FormerDataResponse> getElectricCurrentList(LocalDate start, LocalDate end, Long port) {
        return electricCurrentRepository.findAllByDateBetweenAndPort(start, end, port)
                .stream()
                .map(FormerData::toResponse)
                .collect(Collectors.toList());
    }
}
