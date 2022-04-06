package com.graduatioinProject.sensorMonitoring.node.nodeDetail;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RequestMapping("api/formerData")
@RequiredArgsConstructor
@RestController
public class NodeDetailController {

    private final NodeDetailService nodeDetailService;
    private final ResponseService responseService;

    @GetMapping("/NodeDetail")
    public SingleResult<NodeDetail> getTemperatureList(@RequestBody HashMap<String, Object> body) {
        Long nodePort = Long.valueOf((String) body.get("nodePort"));

        /**
         * Long userCode = Long.valueOf((String) body.get("userCode"));
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
}
