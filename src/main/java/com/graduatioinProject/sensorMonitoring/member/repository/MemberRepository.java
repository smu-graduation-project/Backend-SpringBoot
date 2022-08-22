package com.graduatioinProject.sensorMonitoring.member.repository;

import com.graduatioinProject.sensorMonitoring.member.dto.MemberRes;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByUsername(String username);

	Optional<Member> findByEmployeeNumber(String employeeNumber);

	Optional<Member> findByRefreshToken(String refreshToken);

	boolean existsByUsername(String username);
}
