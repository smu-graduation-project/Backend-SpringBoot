package com.graduatioinProject.sensorMonitoring.productData.site.dto;

import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import lombok.Data;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Data
public class SiteRequest {
    private String name;
    private String type;
    private String information;

    private double gpsXPos;
    private double gpsYPos;

    public Site toEntity() {
        Site entity = Site
                .builder()
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .gpsXPos(this.gpsXPos)
                .gpsYPos(this.gpsYPos)
                .build();

        return entity;
    }
}
