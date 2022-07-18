package com.graduatioinProject.sensorMonitoring.productData.site.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/10
 */

@Entity
@Getter
@Setter
@ToString
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

    private double gpsXPos;
    private double gpsYPos;

    @OneToMany(targetEntity = Battery.class, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Battery> batteries;


    public void addBattery(Battery battery) {
        battery.setSite(this);
        this.batteries.add(battery);
    }

    public SiteResponse toResponse() {
        return SiteResponse.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .gpsXPos(this.gpsXPos)
                .gpsYPos(this.gpsYPos)
                .build();
    }
}
