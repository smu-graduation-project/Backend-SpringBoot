package com.graduatioinProject.sensorMonitoring.productData.node.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodePutRequest;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeGetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/product/node")
@RequiredArgsConstructor
@RestController
public class NodeController {

    private final NodeService nodeService;
    private final ResponseService responseService;

    @GetMapping("/nodeDetail")
    public SingleResult<Node> getNodeDetail(@RequestBody NodeGetRequest request) {
        /**
         * Long userCode = Long.valueOf((String) body.get("userCode"));
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.
         */

        try {
            return  responseService.singleResult(
                    nodeService.getNode(request.getId()).get());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @PutMapping("/addNode")
    public CommonResult setNodeDetail(@RequestBody NodePutRequest request){
        try {
            /**
             * 이후에 유저 권한 확인
             */
            nodeService.setNode(request.toNode());
            return  responseService.successResult();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
