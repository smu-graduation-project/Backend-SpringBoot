package com.graduatioinProject.sensorMonitoring.productData.site.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Data
@Builder
public class SiteResponse {
    private Long id;
    private String name;
    private String type;
    private String information;
    private String address;

    public Site toEntity() {
        return Site.builder()
                .id(id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .address(this.address)
                .build();
    }
}