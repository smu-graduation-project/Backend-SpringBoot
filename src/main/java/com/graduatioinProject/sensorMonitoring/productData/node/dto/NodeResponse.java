package com.graduatioinProject.sensorMonitoring.productData.node.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
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
    private Long port;
    private String imageUrl;
    private String name;
    private String type;

    private String information;
    private Battery battery;
}
