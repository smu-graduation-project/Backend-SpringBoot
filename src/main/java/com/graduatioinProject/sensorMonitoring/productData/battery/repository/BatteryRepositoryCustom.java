package com.graduatioinProject.sensorMonitoring.productData.battery.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithNode;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithSite;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.QBattery;
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
public class BatteryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public BatteryResponseWithNode findByIdWithNode(Long id) {
        QBattery qBattery = QBattery.battery;
        QNode qNode = QNode.node;

        Battery battery = jpaQueryFactory
                .selectFrom(qBattery)
                .join(qBattery.node).fetchJoin()
                .where(qBattery.id.eq(id))
                .fetchOne();

        assert battery != null;
        return battery.toResponseWithNode();
    }

    public Battery findByIdWithSite(Long id) {
        QBattery qBattery = QBattery.battery;
        QSite qSite = QSite.site;
        Battery battery = jpaQueryFactory
                .selectFrom(qBattery)
                .join(qBattery.site, qSite).fetchJoin()
                .where(qBattery.id.eq(id))
                .fetchOne();

        assert battery != null;
        return battery;
    }
}
