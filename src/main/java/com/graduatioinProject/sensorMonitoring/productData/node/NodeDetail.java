package com.graduatioinProject.sensorMonitoring.productData.node;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class NodeDetail {
    @Id
    private Long nodeId;

    private String imageUrl;

    /**
     * node 정보(기기 명, type 등)
     */
    private String information;

    // 노드 상세정보에 들어갈 데이터 목록(4/12)
    /**
     * 소속 Site
     * BaterryPack
     * double[] GPS;
     */

    /**
     * ManyToMany로
     * User와 Node의 접근허가 관련 relation ship 만들기
     */

}
