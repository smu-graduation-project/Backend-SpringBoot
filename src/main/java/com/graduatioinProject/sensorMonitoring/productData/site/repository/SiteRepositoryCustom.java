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

    public Site findByIdMemberRole(Long id) {
        QSite qSite = QSite.site;
        return jpaQueryFactory
                .selectFrom(qSite)
                .join(qSite.member).fetchJoin()
                .where(qSite.id.eq(id))
                .fetchOne();
    }
}
