package com.graduatioinProject.sensorMonitoring.productData.node;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NodeDetailRepository extends JpaRepository<NodeDetail, Long> {
    public Optional<NodeDetail> findByNodePort(Long nodePort);
}