package com.graduatioinProject.sensorMonitoring.productData.node.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeWithBattery;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long port;

    private String name;
    private String type;
    private String information;

    @ManyToOne(targetEntity = Battery.class, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Battery battery;

    public NodeResponse toResponse() {
        return NodeResponse.builder()
                .port(this.port)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .build();
    }

    public NodeWithBattery toResponseWithBattery() {
        return NodeWithBattery.builder()
                .port(this.port)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .batteryWithSite(this.battery.toResponseWithSite())
                .build();
    }
}
