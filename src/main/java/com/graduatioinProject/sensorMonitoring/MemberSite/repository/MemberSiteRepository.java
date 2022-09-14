package com.graduatioinProject.sensorMonitoring.MemberSite.repository;

import com.graduatioinProject.sensorMonitoring.MemberSite.entity.MemberSite;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/09/13
 */

@Repository
public interface MemberSiteRepository extends JpaRepository<MemberSite, Long> {
    List<MemberSite> findAllByMember(Member member);
}
