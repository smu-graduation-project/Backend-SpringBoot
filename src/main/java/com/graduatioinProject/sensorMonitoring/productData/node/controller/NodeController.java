package com.graduatioinProject.sensorMonitoring.productData.node.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt.JwtProperties;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithNode;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeRequest;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Api(tags = "06. 노드")
@RequestMapping("api/product/node")
@RequiredArgsConstructor
@RestController
public class NodeController {

    private final NodeService nodeService;
    private final BatteryService batteryService;
    private final ResponseService responseService;

    @ApiOperation(value = "노드 추가", notes = "노드 관련 정보를 받아 노드를 추가(프론트에서 처리 X)")
    @PostMapping("/add/{port}")
    public CommonResult setNodeDetail(@PathVariable Long port){
        /**
         * 노드 정보는 자동으로 추가되도록 하고,
         * 이를 수정하는 것을 직접 하도록 설정하는 것은 어떤지??
         * 그렇게 하려면, 특정 IP만 가능하도록 설정하거나, 따로 암호화된 키를 가져야 할 것 같음.
         */
        try {
            nodeService.save(port);
            return responseService.successResult();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @ApiOperation(value = "노드 수정", notes = "노드 관련 정보를 받아 노드정보를 수정합니다.")
    @PutMapping("/update/{batteryId}/{nodeId}")
    public CommonResult setNodeDetail( HttpServletRequest httpServletRequest,
                                       @RequestBody NodeRequest nodeUpdateRequest,
                                       @PathVariable Long batteryId,
                                       @PathVariable Long nodeId) {

//        if (!nodeService.checkNode(batteryId)) {
//            return responseService.failResult(ExMessage.NODE_ERROR_NOT_FOUND.getMessage());
//        }
        nodeService.update(nodeUpdateRequest, batteryId, nodeId);
        return responseService.successResult();
    }

    @ApiOperation(value = "노드 상세정보", notes = "노드 id를 받아 해당하는 노드의 상세정보를 반환")
    @GetMapping("/detail/{id}")
    public SingleResult<NodeResponse> getNodeDetail(HttpServletRequest httpServletRequest,
                                                    @PathVariable Long id) {
        try {
            return responseService.singleResult(nodeService.findByIdResponse(id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }

    }

    @ApiOperation(value = "노드 리스트(batteryId)", notes = "해당 배터리와 연결된 모든 node의 정보를 반환")
    @GetMapping("list/{batteryId}")
    public ListResult<NodeResponse> getAllNodeByBattery(HttpServletRequest httpServletRequest,
                                                        @PathVariable Long batteryId) {
        BatteryResponseWithNode batteryResponseWithNode = batteryService.findByIdWithNode(batteryId);
        return responseService.listResult(batteryResponseWithNode.getNodeResponses());
    }

    @ApiOperation(value = "노드 리스트(member)", notes = "해당 아이디로 접근 가능한 모든 node의 정보를 반환")
    @GetMapping("/all")
    public ListResult<NodeResponse> getAllNode(HttpServletRequest httpServletRequest) {
        String userName = httpServletRequest.getHeader(JwtProperties.USERNAME);
        /**
         * 페이징을 적용해야할지 의문
         */
        return responseService.listResult(
                nodeService.findAll()
                        .stream()
                        .filter(i -> nodeService.chekMemberAuthorityUser(userName, i.getId()))
                        .map(Node::toResponse)
                        .collect(Collectors.toList()));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult delteNode(HttpServletRequest httpServletRequest,
                                   @PathVariable Long id) {
        nodeService.delete(id);
        return responseService.successResult();
    }
}
