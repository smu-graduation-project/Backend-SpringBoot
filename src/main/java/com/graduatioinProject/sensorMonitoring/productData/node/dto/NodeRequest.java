package com.graduatioinProject.sensorMonitoring.productData.node.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NodeRequest {
    private String name;
    private String type;
    private String information;

    public Node toEntity() {
        return Node.builder()
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .build();
    }
}
