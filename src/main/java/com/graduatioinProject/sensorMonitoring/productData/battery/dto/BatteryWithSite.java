package com.graduatioinProject.sensorMonitoring.productData.battery.dto;

import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/07/06
 */

@Data
@Builder
public class BatteryWithSite {
    private Long id;

    private String name;
    private String type;
    private String information;

    private String imageUrl;

    private SiteResponse siteResponse;
}
