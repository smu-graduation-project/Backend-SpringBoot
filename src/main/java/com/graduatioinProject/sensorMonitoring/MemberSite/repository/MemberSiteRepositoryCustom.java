package com.graduatioinProject.sensorMonitoring.MemberSite.repository;

import com.graduatioinProject.sensorMonitoring.MemberSite.entity.MemberSite;
import com.graduatioinProject.sensorMonitoring.MemberSite.entity.QMemberSite;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/09/13
 */

@Repository
@RequiredArgsConstructor
public class MemberSiteRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public List<MemberSite> getSiteIdList(String memberName) {
        QMemberSite qMemberSite = QMemberSite.memberSite;
        return jpaQueryFactory
                .selectFrom(qMemberSite)
                .where(qMemberSite.member.username.eq(memberName))
                .fetch();
    }

    public List<MemberSite> getSiteIdListALL() {
        QMemberSite qMemberSite = QMemberSite.memberSite;
        return jpaQueryFactory
                .selectFrom(qMemberSite)
                .fetch();
    }
}

