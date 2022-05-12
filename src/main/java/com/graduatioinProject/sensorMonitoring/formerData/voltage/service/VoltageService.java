package com.graduatioinProject.sensorMonitoring.formerData.voltage.service;

import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.voltage.repository.VoltageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoltageService {
    private final VoltageRepository voltageRepository;

    @Transactional(readOnly = true)
    public List<FormerDataResponse> findVoltageList(LocalDate start, LocalDate end, Long port) {
        return voltageRepository.findAllByDateBetweenAndPort(start, end, port)
                .stream()
                .map(FormerData::toResponse)
                .collect(Collectors.toList());
    }
}