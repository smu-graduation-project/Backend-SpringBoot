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

    public Battery toEntity() {
        return Battery.builder()
                .id(id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .imageUrl(this.imageUrl)
                .build();
    }
}
