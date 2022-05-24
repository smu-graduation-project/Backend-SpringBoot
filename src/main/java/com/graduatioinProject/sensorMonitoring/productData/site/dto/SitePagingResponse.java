package com.graduatioinProject.sensorMonitoring.productData.site.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/24
 */


@Data
@Builder
public class SitePagingResponse {
    private String name;
    private String type;

    private double gpsXPos;
    private double gpsYPos;
}
