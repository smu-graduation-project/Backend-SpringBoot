package com.graduatioinProject.sensorMonitoring.MemberSite.repository;

import com.graduatioinProject.sensorMonitoring.MemberSite.entity.MemberSite;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.Sides;
import java.util.List;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/09/13
 */

@Repository
public interface MemberSiteRepository extends JpaRepository<MemberSite, Long> {
    List<MemberSite> findAllByMember(Member member);

    MemberSite findTopByMemberAndSite(Member member, Site site);

    void deleteAllByMemberAndSite(Member member, Site site);
}
