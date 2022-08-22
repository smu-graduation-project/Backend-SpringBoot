package com.graduatioinProject.sensorMonitoring.productData.node.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponseWithAll;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponseWithBattery;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "battery_id")
    private Battery battery;

    public NodeResponse toResponse() {
        return NodeResponse.builder()
                .id(id)
                .port(this.port)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .build();
    }

    public NodeResponseWithBattery toResponseWithBattery() {
        return NodeResponseWithBattery.builder()
                .id(id)
                .port(this.port)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .batteryResponse(this.battery.toResponse())
                .build();
    }

    public NodeResponseWithAll toResponseWithAll() {
        return NodeResponseWithAll.builder()
                .id(id)
                .port(this.port)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .batteryResponseWithSite(this.battery.toResponseWithSite())
                .build();
    }
}
