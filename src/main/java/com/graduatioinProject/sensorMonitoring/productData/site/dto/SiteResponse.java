package com.graduatioinProject.sensorMonitoring.productData.site.dto;

import lombok.Builder;
import lombok.Data;


/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Data
@Builder
public class SiteResponse {
    private String name;
    private String type;
    private String information;

    private double gpsXPos;
    private double gpsYPos;
}