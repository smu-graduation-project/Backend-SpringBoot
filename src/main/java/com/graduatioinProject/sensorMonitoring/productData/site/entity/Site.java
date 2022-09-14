package com.graduatioinProject.sensorMonitoring.productData.site.entity;

import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponseWithBattery;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/10
 */

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String information;
    private String address;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "site")
    private List<Battery> battery;

    public SiteResponse toResponse() {
        return SiteResponse.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .address(this.address)
                .build();
    }

    public SiteResponseWithBattery toResponseWithBattery() {
        return SiteResponseWithBattery.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .address(this.address)
                .batteryResponse(this.battery.stream().map(Battery::toResponse).collect(Collectors.toList()))
                .build();
    }
}
