package com.graduatioinProject.sensorMonitoring.productData.node.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeUpdateRequest;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepository;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepositoryCustom;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void save(NodeUpdateRequest nodeUpdateRequest) {
        nodeRepository.save(nodeUpdateRequest.toEntity());
    }

    public void saveNew(Long port) {
        nodeRepository.save(Node.builder().port(port).build());
    }

    public Boolean checkNode(Long id) {
        return nodeRepository.findById(id).isPresent();
    }

    public Boolean chekMemberAuthorityUser(String userName, Long nodeId) {
        SiteResponse siteResponse = nodeRepositoryCustom.findByIdSite(nodeId).getBatteryWithSite().getSiteResponse();
        List<Long> siteIdList = memberService.findByUserNameWithSiteIdList(userName);
        return siteIdList.contains(siteResponse.getId());
    }

    public List<Node> findAll(){
        return nodeRepository.findAll();
    }


    public void delete(Long id) {
        nodeRepository.deleteById(id);
    }
}
