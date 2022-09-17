package com.graduatioinProject.sensorMonitoring.productData.battery.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/08/21
 */

@Data
@Builder
public class BatteryResponseWithNode {
    private Long id;

    private String name;
    private String type;
    private String information;

    private String imageUrl;
    private List<NodeResponse> nodeResponse;

    public Battery toEntity() {
        return Battery.builder()
                .id(id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .imageUrl(this.imageUrl)
                .node(this.nodeResponse.stream().map(NodeResponse::toEntity).collect(Collectors.toList()))
                .build();
    }
}