package com.graduatioinProject.sensorMonitoring.formerData.Controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.SessionService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.service.FormerDataService;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;

import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/15
 */


@Api(tags = "03. 이전 데이터")
@CrossOrigin
@RequestMapping("api/formerData")
@RestController
@RequiredArgsConstructor
public class FormerDataController {
    private final SessionService sessionService;
    private final FormerDataService formerDataService;
    private final ResponseService responseService;
    private final NodeService nodeService;

    @GetMapping("/electricCurrent/list/{id}")
    @ApiOperation(value = "전류 이전 데이터 목록", notes = "날짜와 id 받아 전류 이전 데이러 목록을 반환")
    public CommonResult getElectricCurrentList(@PathVariable Long id,
                                               HttpServletRequest httpServletRequest,
                                               FormerDataRequest request) {
        check(id, httpServletRequest);

        try {
            List<FormerDataResponse> result = formerDataService.getElectricCurrentList(request.getStartDate(), request.getEndDate(), id);
            return  responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @GetMapping("/temperature/list/{id}")
    @ApiOperation(value = "온도 이전 데이터 목록", notes = "날짜와 id를 받아 온도 이전 데이러 목록을 반환")
    public CommonResult getTemperatureList(@PathVariable Long id,
                                           HttpServletRequest httpServletRequest,
                                           FormerDataRequest request) {
        check(id, httpServletRequest);

        try {
            List<FormerDataResponse> result = formerDataService.getTemperatureList(request.getStartDate(), request.getEndDate(), id);
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @GetMapping("/voltage/list/{id}")
    @ApiOperation(value = "전압 이전 데이터 목록", notes = "날짜와 id를 받아 전압 이전 데이러 목록을 반환")
    public CommonResult getVoltageList(@PathVariable Long id,
                                       HttpServletRequest httpServletRequest,
                                       FormerDataRequest request) {

        check(id, httpServletRequest);

        try {
            List<FormerDataResponse> result = formerDataService.getVoltageList(request.getStartDate(), request.getEndDate(), id);
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    /**
     * not api method
     */

    /**
     * 세션정보 확인
     * @param id
     * @param httpServletRequest
     */
    private void check(Long id, HttpServletRequest httpServletRequest) {
        checkMemberAuthority(httpServletRequest);
        checkNode(id);
    }

    /**
     * login 정보 확인
     * @param httpServletRequest
     */
    private void checkMemberAuthority(HttpServletRequest httpServletRequest) {
        MemberSessionDto loginMember = sessionService.checkMemberSession(httpServletRequest);
        /**
         * 요청한 유저가 해당 NodePort에 접근권한이 있는지 확인
         *
         * memberDto에 관련 정보가 있으면, 이를 기반으로 권한을 빠르게 체크할 수 있을 듯.
         */
    }

    /**
     * 해당 node가 존재하는지
     * @param id
     */
    private void checkNode(Long id) {
        if (!nodeService.checkNode(id)) {
            throw (new BussinessException(ExMessage.NODE_ERROR_NOT_FOUND.getMessage()));
        }
    }
}