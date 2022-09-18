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

    private String address;

    public Site toEntity() {
        return Site
                .builder()
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .address(this.address)
                .build();
    }
}
