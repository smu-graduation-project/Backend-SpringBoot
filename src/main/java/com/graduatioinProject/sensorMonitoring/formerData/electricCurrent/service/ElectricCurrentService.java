package com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.service;

import com.graduatioinProject.sensorMonitoring.formerData.FormerData;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.electricCurrent.repository.ElectricCurrentRepository;
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
public class ElectricCurrentService {
    private final ElectricCurrentRepository electricCurrentRepository;

    @Transactional(readOnly = true)
    public List<FormerDataResponse> findElectricCurrentList(LocalDate start, LocalDate end, Long port) {
        return electricCurrentRepository.findAllByDateBetweenAndPort(start, end, port)
                .stream()
                .map(FormerData::toResponse)
                .collect(Collectors.toList());
    }
}