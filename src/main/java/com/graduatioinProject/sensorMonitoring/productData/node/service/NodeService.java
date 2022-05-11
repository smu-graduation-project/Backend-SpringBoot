package com.graduatioinProject.sensorMonitoring.productData.node.service;

import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NodeService {
    private final NodeRepository nodeDetailRepository;

    public Optional<Node> getNode(Long node) {
        return nodeDetailRepository.findByNode(node);
    }

    public Long setNode(Node node) {
        nodeDetailRepository.save(node);
        return node.getNodeId();
    }
}
