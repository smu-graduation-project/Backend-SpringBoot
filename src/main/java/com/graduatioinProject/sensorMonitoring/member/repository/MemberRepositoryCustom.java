package com.graduatioinProject.sensorMonitoring.member.repository;

import com.graduatioinProject.sensorMonitoring.member.dto.MemberResWithSite;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.member.entity.QMember;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.QSite;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/07/05
 */

@RequiredArgsConstructor
@Repository
public class MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public List<SiteResponse> findByUserNameWithSite(String userName) {
        QMember qMember = QMember.member;
        QSite qSite = QSite.site;

        return jpaQueryFactory
                .select(qSite)
                .from(qMember)
                .join(qSite).fetchJoin()
                .where(qMember.username.eq(userName))
                .fetch()
                .stream()
                .map(Site::toResponse)
                .collect(Collectors.toList());
    }

    public List<Long> findByUserNameSiteIdList(String userName) {
        QMember qMember = QMember.member;
        QSite qSite = QSite.site;

        return jpaQueryFactory
                .select(qSite.id)
                .from(qMember)
                .join(qSite)
                .where(qMember.username.eq(userName))
                .fetch();
    }
}

