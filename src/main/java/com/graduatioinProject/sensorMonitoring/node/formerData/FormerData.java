package com.graduatioinProject.sensorMonitoring.node.formerData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class FormerData {
    private LocalDate date;
    private Long nodePort;

    private double max;
    private double min;
    private double average;

    // 노드가 여러개가 되면, 데이터가 늘어날 수 있다.
    // id를 따로 만들지, node + date를 Pk로 할지 고민

    // private Long id;
    // private int node;

    // 이후에 전류 전압 추가

    // node를 key로 해당 노드에 접근 가능한 유저들 List를 만들 필요가 있어보인다.
}
