package com.graduatioinProject.sensorMonitoring.productData.battery.dto;

import lombok.Data;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/01
 */

@Data
public class BatteryRequest {
    private String name;
    private String type;
    private String information;

    private Long siteId;
}
