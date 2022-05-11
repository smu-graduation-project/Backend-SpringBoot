package com.graduatioinProject.sensorMonitoring.member.repository;

import com.graduatioinProject.sensorMonitoring.member.dto.MemberRes;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByUserId(String userId);

	boolean existsByUserId(String userId);
}