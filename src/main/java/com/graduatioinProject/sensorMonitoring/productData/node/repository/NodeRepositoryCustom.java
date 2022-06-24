package com.graduatioinProject.sensorMonitoring.productData.node.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.QBattery;
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

    public Node findByIdMemberRole(Long id) {
        QBattery qBattery = QBattery.battery;
        QNode qNode = QNode.node;
        QSite qSite = QSite.site;
        return jpaQueryFactory
                .selectFrom(qNode)
                .join(qNode.battery, qBattery).fetchJoin()
                .join(qBattery.site, qSite).fetchJoin()
                .join(qSite.member).fetchJoin()
                .where(qNode.id.eq(id))
                .fetchOne();
    }

    public Node findByIdBattery(Long id) {
        QBattery qBattery = QBattery.battery;
        QNode qNode = QNode.node;
        return jpaQueryFactory
                .selectFrom(qNode)
                .join(qNode.battery, qBattery).fetchJoin()
                .where(qNode.id.eq(id))
                .fetchOne();
    }
}
