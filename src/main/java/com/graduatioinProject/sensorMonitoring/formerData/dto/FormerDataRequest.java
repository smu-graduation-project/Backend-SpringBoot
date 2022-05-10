package com.graduatioinProject.sensorMonitoring.formerData.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormerDataRequest {
    /**
     * +a : 로그인 관련 정보
     */
    private LocalDate startDate;
    private LocalDate endDate;
    private Long nodePort;
}
