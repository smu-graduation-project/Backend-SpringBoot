package com.graduatioinProject.sensorMonitoring.productData.battery.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
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

    //private Long siteId;
    public Battery toEntity() {
        return Battery.builder()
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .build();
    }
}
