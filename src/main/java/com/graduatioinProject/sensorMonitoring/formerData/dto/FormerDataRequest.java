package com.graduatioinProject.sensorMonitoring.formerData.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/09
 */

@Data
public class FormerDataRequest {
    /**
     * +a : 로그인 관련 정보
     */

    private LocalDate startDate;
    private LocalDate endDate;
}
