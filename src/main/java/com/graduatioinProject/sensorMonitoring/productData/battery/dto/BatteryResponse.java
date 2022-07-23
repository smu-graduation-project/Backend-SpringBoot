package com.graduatioinProject.sensorMonitoring.productData.battery.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/07/01
 */

@Data
@Builder
public class BatteryResponse {
    private Long id;

    private String name;
    private String type;
    private String information;

    private String imageUrl;
}
