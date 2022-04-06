package com.graduatioinProject.sensorMonitoring.node.nodeDetail;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class NodeDetail {
    @Id
    private Long nodePort;
    private String imageUrl;

    // @OneToMany(mappedBy =)
    // private List<> user;

    // Entity 하나 더 만들어서, node별로 사용가능한 유저 List 저장하기

    private String information;


    // 노드 상세정보에 들어갈 데이터 목록

    // 소속 Site
    // Battery Package
    // BateryCell 정보
    // 위치(GPS)
}
