package com.graduatioinProject.sensorMonitoring.productData.node.repository;

import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
}
