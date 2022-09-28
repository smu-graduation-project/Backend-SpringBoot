package com.graduatioinProject.sensorMonitoring.productData.node.service;

import com.graduatioinProject.sensorMonitoring.MemberSite.service.MemberSiteService;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeRequest;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepository;
import com.graduatioinProject.sensorMonitoring.productData.node.repository.NodeRepositoryCustom;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NodeService {
    private final NodeRepository nodeRepository;
    private final BatteryService batteryService;
    private final NodeRepositoryCustom nodeRepositoryCustom;
    private final MemberSiteService memberSiteService;

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

    @Transactional(rollbackFor = Exception.class)
    public void update(NodeRequest nodeUpdateRequest, Long batteryId, Long nodeId) {
        Node node = nodeRepositoryCustom.findByIdBattery(nodeId);
        node.setInformation(nodeUpdateRequest.getInformation());
        node.setType(nodeUpdateRequest.getType());
        node.setName(nodeUpdateRequest.getName());
        node.setBattery(batteryService.findById(batteryId).toEntity());
        nodeRepository.save(node);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Long port) {
        nodeRepository.save(Node.builder().port(port).build());
    }

    public Boolean checkNode(Long id) {
        return nodeRepository.findById(id).isPresent();
    }

    public Boolean chekMemberAuthorityUser(String userName, Long nodeId) {
        SiteResponse siteResponse = nodeRepositoryCustom.findByIdSite(nodeId).getBatteryResponseWithSite().getSiteResponse();
        List<Long> siteIdList = memberSiteService.getSiteIdList(userName);
        return siteIdList.contains(siteResponse.getId());
    }

    public List<NodeResponse> findAll(){
        return nodeRepository.findAll().stream().map(Node::toResponse).collect(Collectors.toList());
    }


    public void delete(Long id) {
        nodeRepository.deleteById(id);
    }
}
