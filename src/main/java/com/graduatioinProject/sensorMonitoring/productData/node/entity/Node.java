package com.graduatioinProject.sensorMonitoring.productData.node.entity;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
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

    private String imageUrl;

    /**
     * node 정보(기기 명, type 등)
     */
    private String name;
    private String type;

    private String information;

    @ManyToOne
    private Battery battery;
    // 노드 상세정보에 들어갈 데이터 목록(2022-04-12)
    /**
     * 소속 Site
     * BaterryPack
     * double[] GPS;
     */

    /**
     * ManyToMany로
     * User와 Node의 접근허가 관련 relationship 만들기
     */

}
