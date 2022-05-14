package com.graduatioinProject.sensorMonitoring.productData.node.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodePutRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "08. 노드 상세정보")
@RequestMapping("api/product/node")
@RequiredArgsConstructor
@RestController
public class NodeController {

    private final NodeService nodeService;
    private final ResponseService responseService;

    @ApiOperation(value = "노드 상세정보", notes = "")
    @GetMapping("/nodeDetail/{port}")
    public CommonResult getNodeDetail(@PathVariable Long port) {
        /**
         * Long userCode = Long.valueOf((String) body.get("userCode"));
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.
         */

        Optional<Node> response = nodeService.getNode(port);
        try {
            return responseService.singleResult(response.get().toResponse());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @ApiOperation(value = "노드 추가", notes = "노드 관련 정보를 받아 노드를 추가합니다.")
    @PutMapping("/addNode")
    public CommonResult setNodeDetail(@RequestBody NodePutRequest request){
        /**
         * 유저 권한 확인
         */
        try {
            nodeService.setNode(request.toEntity());
            return  responseService.successResult();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
