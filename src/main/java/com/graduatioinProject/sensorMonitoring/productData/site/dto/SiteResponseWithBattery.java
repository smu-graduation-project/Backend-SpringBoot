package com.graduatioinProject.sensorMonitoring.productData.site.dto;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
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
public class SiteResponseWithBattery {
    private Long id;
    private String name;
    private String type;
    private String information;
    private String address;
    private List<BatteryResponse> batteryResponse;

    public Site toEntity() {
        return Site.builder()
                .id(id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .address(this.address)
                .battery(this.batteryResponse.stream().map(BatteryResponse::toEntity).collect(Collectors.toList()))
                .build();
    }
}