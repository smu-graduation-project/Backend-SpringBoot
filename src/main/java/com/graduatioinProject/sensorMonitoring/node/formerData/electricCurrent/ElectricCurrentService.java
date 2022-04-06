package com.graduatioinProject.sensorMonitoring.node.formerData.electricCurrent;

import com.graduatioinProject.sensorMonitoring.node.formerData.temperature.Temperature;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectricCurrentService {
    private final ElectricCurrentRepository ElectricCurrentRepository;

    public List<ElectricCurrent> findElectricCurrentList(LocalDate start, LocalDate end, Long nodePort) {
        return ElectricCurrentRepository.findAllByDateBetweenAndNodePort(start, end, nodePort);
    }
}