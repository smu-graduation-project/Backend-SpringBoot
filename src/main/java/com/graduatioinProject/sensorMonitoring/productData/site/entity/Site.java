package com.graduatioinProject.sensorMonitoring.productData.site.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
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
    /**
     * Gps, type 등 데이터 추가..
     */

    @OneToMany
    private Set<Battery> batterySet;

}
