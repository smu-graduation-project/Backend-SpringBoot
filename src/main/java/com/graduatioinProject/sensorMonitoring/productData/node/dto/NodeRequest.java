package com.graduatioinProject.sensorMonitoring.productData.node.dto;

import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/07/17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NodeRequest {
    private Long port;

    public Node toEntity() {
        return Node.builder()
                .port(this.port)
                .build();
    }
}