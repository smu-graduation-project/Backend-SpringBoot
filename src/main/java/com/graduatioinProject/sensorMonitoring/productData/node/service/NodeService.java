package com.graduatioinProject.sensorMonitoring.productData.node.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepository;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class NodeService {
    private final NodeRepository nodeDetailRepository;
    private final NodeRepositoryCustom nodeRepositoryCustom;

    public NodeResponse getNodeResponse(Long id) {
        return nodeDetailRepository
                .findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.NODE_ERROR_NOT_FOUND.getMessage()))
                .toResponse();
    }

    public Node getNode(Long id) {
        return nodeDetailRepository
                .findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.NODE_ERROR_NOT_FOUND.getMessage()));
    }

    public Long setNode(Node node) {
        nodeDetailRepository.save(node);
        return node.getId();
    }

    public Boolean checkNode(Long id) {
        return nodeDetailRepository.findById(id).isPresent();
    }

    public Boolean checkMemberRole(Member member, Long nodeId) {
        Set<Member> members = nodeRepositoryCustom.findByIdMemberRole(nodeId).getBattery().getSite().getMembers();
        return members.contains(member);
    }
}
