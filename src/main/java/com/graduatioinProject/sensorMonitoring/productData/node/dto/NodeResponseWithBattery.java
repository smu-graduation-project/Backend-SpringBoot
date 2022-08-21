package com.graduatioinProject.sensorMonitoring.productData.node.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/08/21
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NodeResponseWithBattery {
    private Long id;
    private String name;
    private String type;
    private String information;
    private BatteryResponse batteryResponse;

    public Node toEntity() {
        return Node.builder()
                .id(id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .battery(this.batteryResponse.toEntity())
                .build();
    }
}

