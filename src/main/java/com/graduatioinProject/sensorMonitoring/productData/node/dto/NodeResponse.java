package com.graduatioinProject.sensorMonitoring.productData.node.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/11
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NodeResponse {
    private Long id;
    private Long port;
    private String name;
    private String type;
    private String information;

    public Node toEntity() {
        return Node.builder()
                .id(this.id)
                .port(this.port)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .build();
    }

}
