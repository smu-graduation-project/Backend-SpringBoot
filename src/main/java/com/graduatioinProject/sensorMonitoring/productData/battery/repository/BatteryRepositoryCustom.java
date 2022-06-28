package com.graduatioinProject.sensorMonitoring.productData.battery.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
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
public class BatteryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public Battery findByIdMemberRole(Long id) {
        QBattery qBattery = QBattery.battery;
        QSite qSite = QSite.site;
        return jpaQueryFactory
                .selectFrom(qBattery)
                .join(qBattery.site, qSite).fetchJoin()
                .where(qBattery.id.eq(id))
                .fetchOne();
    }
}
