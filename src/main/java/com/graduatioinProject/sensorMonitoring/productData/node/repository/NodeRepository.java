package com.graduatioinProject.sensorMonitoring.productData.node.repository;

import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    Optional<Node> findByNode(Long node);
}
