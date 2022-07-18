package com.graduatioinProject.sensorMonitoring.productData.battery.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryWithNode;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryWithSite;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String information;

    private String imageName;
//    private String imageUrl;
//    private long image_size;

    @ManyToOne(targetEntity = Site.class, fetch = FetchType.LAZY)
    private Site site;

    @OneToMany(targetEntity = Node.class, fetch = FetchType.LAZY)
    private List<Node> node;


    public BatteryResponse toResponse() {
        return BatteryResponse.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                // .imageUrl(this.imageUrl)
                .build();
    }

    public BatteryWithNode toResponseWithNode() {
        return BatteryWithNode.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                // .imageUrl(this.imageUrl)
                .nodeResponses(this.node.stream()
                        .map(Node::toResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public BatteryWithSite toResponseWithSite() {
        return BatteryWithSite.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                // .imageUrl(this.imageUrl)
                .siteResponse(this.site.toResponse())
                .build();
    }
}
