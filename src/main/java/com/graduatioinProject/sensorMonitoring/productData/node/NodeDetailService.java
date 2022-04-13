package com.graduatioinProject.sensorMonitoring.productData.node;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NodeDetailService {
    private final NodeDetailRepository nodeDetailRepository;

    public Optional<NodeDetail> findNodeDetail(Long nodePort) {
        return nodeDetailRepository.findByNodePort(nodePort);
    }

    public Long setNodeDetail(NodeDetail nodeDetail) {
        nodeDetailRepository.save(nodeDetail);
        return nodeDetail.getNodeId();
    }
}
