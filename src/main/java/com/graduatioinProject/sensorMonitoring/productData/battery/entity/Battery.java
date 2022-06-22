package com.graduatioinProject.sensorMonitoring.productData.battery.entity;

import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
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
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String information;

    private String imageName;
    private String imageUrl;
    private long image_size;

    @ManyToOne(targetEntity = Site.class, fetch = FetchType.LAZY)
    private Site site;
}
