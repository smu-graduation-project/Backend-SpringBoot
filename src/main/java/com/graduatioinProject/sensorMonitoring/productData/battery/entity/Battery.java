package com.graduatioinProject.sensorMonitoring.productData.battery.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithAll;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithNode;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithSite;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
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

    private String imageUrl;
//    private String imageUrl;
//    private long image_size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site site;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "battery")
    private List<Node> node;

    public void addNode(Node addNode) {
        if (this.node == null) {
            this.node = new ArrayList<>();
        }
        this.node.add(addNode);
        addNode.setBattery(this);
    }

    public BatteryResponse toResponse() {
        return BatteryResponse.builder()
                .id(id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                // .imageUrl(this.imageUrl)
                .build();
    }

    public BatteryResponseWithNode toResponseWithNode() {
        return BatteryResponseWithNode.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                // .imageUrl(this.imageUrl)
                .nodeResponses(this.node.stream().map(Node::toResponse).collect(Collectors.toList()))
                .build();
    }

    public BatteryResponseWithSite toResponseWithSite() {
        return BatteryResponseWithSite.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                // .imageUrl(this.imageUrl)
                .siteResponse(this.site.toResponse())
                .build();
    }

    public BatteryResponseWithAll toResponseWithAll() {
        return BatteryResponseWithAll.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                // .imageUrl(this.imageUrl)
                .nodeResponses(this.node.stream().map(Node::toResponse).collect(Collectors.toList()))
                .siteResponse(this.site.toResponse())
                .build();
    }
}

