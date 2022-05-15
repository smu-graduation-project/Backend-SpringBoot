package com.graduatioinProject.sensorMonitoring.productData.node.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;

import com.graduatioinProject.sensorMonitoring.baseUtil.service.SessionService;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.entity.Node;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodePutRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "08. 노드 상세정보")
@RequestMapping("api/product/node")
@RequiredArgsConstructor
@RestController
public class NodeController {

    private final NodeService nodeService;
    private final SessionService sessionService;
    private final ResponseService responseService;

    @ApiOperation(value = "노드 상세정보", notes = "노드 id를 받아 해당하는 노드의 상세정보를 반환")
    @GetMapping("/detail/{id}")
    public CommonResult getNodeDetail(@PathVariable Long id,
                                      HttpServletRequest httpServletRequest) {

        MemberSessionDto loginMember = sessionService.checkMemberSession(httpServletRequest);

        /**
         * Long userCode = Long.valueOf((String) body.get("userCode"));
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인 해야함.
         */

        NodeResponse response = nodeService.getNodeResponse(id);
        try {
            return responseService.singleResult(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @ApiOperation(value = "노드 추가", notes = "노드 관련 정보를 받아 노드를 추가(프론트에서 처리 X)")
    @PutMapping("/add/{port}")
    public CommonResult setNodeDetail(@PathVariable Long port){
        /**
         * 노드 정보는 자동으로 추가되도록 하고,
         * 이를 수정하는 것을 직접 하도록 설정하는 것은 어떤지??
         * 그렇게 하려면, 특정 IP만 가능하도록 설정하거나, 따로 암호화된 키를 가져야 할 것 같음.
         */
        try {
            nodeService.setNode(Node.builder().port(port).build());
            return  responseService.successResult();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @ApiOperation(value = "노드 수정", notes = "노드 관련 정보를 받아 노드정보를 수정합니다.")
    @PutMapping("/update/{id}")
    public CommonResult setNodeDetail(@RequestBody NodePutRequest request,
                                      HttpServletRequest httpServletRequest,
                                      @PathVariable Long id) {

        MemberSessionDto loginMember = sessionService.checkMemberSession(httpServletRequest);
        /**
         * 권한이 관리자인지 확인하기
         */

        if(!nodeService.checkNode(id)) {
            return responseService.failResult(ExMessage.NODE_ERROR_NOT_FOUND.getMessage());
        }

        try {
            nodeService.setNode(request.toEntity());
            return  responseService.successResult();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}
