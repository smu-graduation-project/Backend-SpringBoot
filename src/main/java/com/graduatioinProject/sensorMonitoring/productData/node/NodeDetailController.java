package com.graduatioinProject.sensorMonitoring.productData.node;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RequestMapping("api/product/node")
@RequiredArgsConstructor
@RestController
public class NodeDetailController {

    private final NodeDetailService nodeDetailService;
    private final ResponseService responseService;

    @GetMapping("/nodeDetail")
    public SingleResult<NodeDetail> getNodeDetail(@RequestBody HashMap<String, Object> body) {
        Long nodePort = Long.valueOf((String) body.get("nodePort"));

        /**
         * Long userCode = Long.valueOf((String) body.get("userCode"));ㅎ
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.
         */

        try {
            return  responseService.singleResult(
                    nodeDetailService.findNodeDetail(nodePort).get());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @PutMapping("/addNode")
    public CommonResult setNodeDetail(@RequestBody HashMap<String, Object> body) {
        try {
            // 이후에 node data 추가.
            NodeDetail nodeDetail = new NodeDetail(Long.valueOf((String) body.get("nodeId")), (String) body.get("image"), (String) body.get("imformation"));
            nodeDetailService.setNodeDetail(nodeDetail);

            return  responseService.successResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
