package com.graduatioinProject.sensorMonitoring.formerData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormerDataResponse {
    private LocalDate date;
    private double max;
    private double min;
    private double average;

}
