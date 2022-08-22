package com.graduatioinProject.sensorMonitoring.productData.node.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.QBattery;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponseWithAll;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponseWithBattery;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.QNode;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.QSite;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/21
 */

@Repository
@RequiredArgsConstructor
public class NodeRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public NodeResponseWithAll findByIdSite(Long id) {
        QBattery qBattery = QBattery.battery;
        QNode qNode = QNode.node;
        QSite qSite = QSite.site;
        Node node = jpaQueryFactory
                .selectFrom(qNode)
                .join(qNode.battery, qBattery).fetchJoin()
                .join(qBattery.site, qSite).fetchJoin()
                .where(qNode.id.eq(id))
                .fetchOne();

        assert node != null;
        return node.toResponseWithAll();
    }

    public NodeResponseWithBattery findByIdBattery(Long id) {
        QBattery qBattery = QBattery.battery;
        QNode qNode = QNode.node;
        Node node = jpaQueryFactory
                .selectFrom(qNode)
                .join(qNode.battery, qBattery).fetchJoin()
                .where(qNode.id.eq(id))
                .fetchOne();

        assert node != null;
        return node.toResponseWithBattery();
    }
}
