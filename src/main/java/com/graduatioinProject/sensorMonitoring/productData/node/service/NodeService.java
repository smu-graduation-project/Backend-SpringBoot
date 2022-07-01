package com.graduatioinProject.sensorMonitoring.productData.node.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepository;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepositoryCustom;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NodeService {
    private final NodeRepository nodeRepository;
    private final NodeRepositoryCustom nodeRepositoryCustom;
    private final MemberService memberService;

    public NodeResponse findByIdResponse(Long id) {
        return nodeRepository
                .findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.NODE_ERROR_NOT_FOUND.getMessage()))
                .toResponse();
    }

    public Node findById(Long id) {
        return nodeRepository
                .findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.NODE_ERROR_NOT_FOUND.getMessage()));
    }

    public Long save(Node node) {
        nodeRepository.save(node);
        return node.getId();
    }

    public Boolean checkNode(Long id) {
        return nodeRepository.findById(id).isPresent();
    }

    public Boolean chekMemberAuthorityUser(Long memberId, Long nodeId) {
        Site site = nodeRepositoryCustom.findByIdMemberRole(nodeId).getBattery().getSite();

//        Member member = memberService.findById(memberId);
//        return member.getSites().contains(site);

        // 임시
        return true;
    }

    public List<Node> findAll(){
        return nodeRepository.findAll();
    }

}
