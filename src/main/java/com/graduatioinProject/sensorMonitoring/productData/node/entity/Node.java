package com.graduatioinProject.sensorMonitoring.productData.node.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nodeId;

    @Column(unique = true)
    private Long port;

    private String name;
    private String type;
    private String information;

    @ManyToOne(targetEntity = Battery.class)
    private Battery battery;

    public NodeResponse toResponse() {
        NodeResponse response = NodeResponse.builder()
                .port(this.port)
                .name(this.name)
                .type(this.type)
                .information(this.information)
                .battery(this.battery)
                .build();

        return response;
    }

}
