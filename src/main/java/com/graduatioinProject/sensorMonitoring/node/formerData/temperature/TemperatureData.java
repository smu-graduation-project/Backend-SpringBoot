package com.graduatioinProject.sensorMonitoring.node.formerData.temperature;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Getter
@Data
@Entity
@AllArgsConstructor
public class TemperatureData {
    @Id
    private LocalDate date;

    private double maxTemperature;
    private double minTemperature;
    private double averageTemperature;

    public TemperatureData() {

    }


    // 이후에 전류 전압 추가
}