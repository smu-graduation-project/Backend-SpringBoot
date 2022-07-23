package com.graduatioinProject.sensorMonitoring.productData.node.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryWithSite;
import lombok.Builder;
import lombok.Data;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/07/16
 */

@Data
@Builder
public class NodeWithBattery {
    private Long id;
    private Long port;
    private String name;
    private String type;
    private String information;
    private BatteryWithSite batteryWithSite;
}
