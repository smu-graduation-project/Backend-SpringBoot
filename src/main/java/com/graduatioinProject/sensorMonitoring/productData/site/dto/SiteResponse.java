package com.graduatioinProject.sensorMonitoring.productData.site.dto;

import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import lombok.Builder;
import lombok.Data;


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

    private double gpsXPos;
    private double gpsYPos;

    public Site toEntity() {
        return Site.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .gpsXPos(this.gpsXPos)
                .gpsYPos(this.gpsYPos)
                .build();
    }
}