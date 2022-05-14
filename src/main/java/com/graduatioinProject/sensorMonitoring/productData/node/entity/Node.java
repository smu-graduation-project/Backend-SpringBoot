package com.graduatioinProject.sensorMonitoring.productData.node.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
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

    /**
     * ManyToMany로
     * User와 Node의 접근허가 관련 relationship 만들기
     */

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
