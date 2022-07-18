package com.graduatioinProject.sensorMonitoring.productData.site.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.QBattery;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.QNode;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.QSite;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/21
 */

@Repository
@RequiredArgsConstructor
public class SiteRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public Site findByIdWithBattery(Long id) {
        QSite qSite = QSite.site;
        QBattery qBattery = QBattery.battery;

        return jpaQueryFactory
                .selectFrom(qSite)
                .join(qSite.batteries).fetchJoin()
                .where(qSite.id.eq(id))
                .fetchOne();
    }

    public Site findByIdWithMember(Long id) {
        QSite qSite = QSite.site;
        return jpaQueryFactory
                .selectFrom(qSite)
                .where(qSite.id.eq(id))
                .fetchOne();
    }
}
