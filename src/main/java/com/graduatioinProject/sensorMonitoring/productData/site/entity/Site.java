package com.graduatioinProject.sensorMonitoring.productData.site.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SitePagingResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/10
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String information;

    private double gpsXPos;
    private double gpsYPos;

    @OneToMany(targetEntity = Battery.class)
    private Set<Battery> batteries;

    public SiteResponse toResponse() {

        return SiteResponse
                .builder()
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .gpsXPos(this.gpsXPos)
                .gpsYPos(this.gpsYPos)
                .build();
    }

    public SitePagingResponse toPagingResponse() {

        return SitePagingResponse
                .builder()
                .name(this.name)
                .gpsXPos(this.gpsXPos)
                .gpsYPos(this.gpsYPos)
                .build();
    }
}
