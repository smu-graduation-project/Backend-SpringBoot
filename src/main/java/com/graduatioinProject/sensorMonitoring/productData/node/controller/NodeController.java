package com.graduatioinProject.sensorMonitoring.productData.node.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.BatteryUser;
import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheck;
import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheckAdmin;
import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.NodeUser;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.member.dto.Role;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeResponse;
import com.graduatioinProject.sensorMonitoring.productData.node.dto.NodeRequest;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Api(tags = "06. 노드")
@CrossOrigin
@RequestMapping("api/product/node")
@RequiredArgsConstructor
@RestController
public class NodeController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final SiteService siteService;
    private final NodeService nodeService;
    private final BatteryService batteryService;
    private final ResponseService responseService;


    @ApiOperation(value = "노드 추가", notes = "노드 관련 정보를 받아 노드를 추가(프론트에서 처리 X)")
    @PostMapping("/add/{port}")
    public CommonResult setNodeDetail(@PathVariable Long port){
        try {
            nodeService.save(port);
            return responseService.successResult();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @LoginCheckAdmin
    @ApiOperation(value = "노드 수정", notes = "노드 관련 정보를 받아 노드정보를 수정합니다.")
    @PutMapping("/update/{batteryId}/{nodeId}")
    public CommonResult setNodeDetail( HttpServletRequest httpServletRequest,
                                       @RequestBody NodeRequest nodeUpdateRequest,
                                       @PathVariable Long batteryId,
                                       @PathVariable Long nodeId) {

        nodeService.update(nodeUpdateRequest, batteryId, nodeId);
        return responseService.successResult();
    }

    @NodeUser
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

    @BatteryUser
    @ApiOperation(value = "노드 리스트(batteryId)", notes = "해당 배터리와 연결된 모든 node의 정보를 반환")
    @GetMapping("list/{batteryId}")
    public CommonResult getAllNodeByBattery(HttpServletRequest httpServletRequest,
                                                        @PathVariable Long batteryId) {
        try {
            return responseService.listResult(batteryService.findByIdWithNode(batteryId).getNodeResponse());
        } catch (Exception e){
            return responseService.failResult(ExMessage.DATA_ERROR_NOT_FOUND.getMessage());
        }
    }

    @LoginCheck
    @ApiOperation(value = "노드 리스트(member)", notes = "해당 아이디로 접근 가능한 모든 node의 정보를 반환")
    @GetMapping("/all")
    public CommonResult getAllNode(HttpServletRequest httpServletRequest) {

        try {
//            String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
//            if (memberService.findByUsername(userName).getRole().equals(Role.ADMIN.getName())) {
//                return responseService.listResult(nodeService.findAll());
//            }
//            return responseService.listResult(
//                    nodeService.findAll()
//                            .stream()
//                            .filter(i -> nodeService.chekMemberAuthorityUser(userName, i.getId()))
//                            .collect(Collectors.toList()));
            return responseService.listResult(nodeService.findAll());
        } catch (Exception e){
            return responseService.failResult(ExMessage.DATA_ERROR_NOT_FOUND.getMessage());
        }
    }

    @LoginCheckAdmin
    @ApiOperation(value = "노드 삭제", notes = "해당 node 삭제")
    @DeleteMapping("/delete/{id}")
    public CommonResult delteNode(HttpServletRequest httpServletRequest,
                                   @PathVariable Long id) {
        nodeService.delete(id);
        return responseService.successResult();
    }
}
