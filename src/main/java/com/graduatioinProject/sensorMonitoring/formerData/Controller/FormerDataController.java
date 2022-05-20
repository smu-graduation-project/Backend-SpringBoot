package com.graduatioinProject.sensorMonitoring.formerData.Controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.annotation.LoginCheck;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataRequest;
import com.graduatioinProject.sensorMonitoring.formerData.dto.FormerDataResponse;
import com.graduatioinProject.sensorMonitoring.formerData.service.FormerDataService;

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

    private final FormerDataService formerDataService;
    private final ResponseService responseService;
    private final NodeService nodeService;

    @LoginCheck
    @GetMapping("/electricCurrent/list/{id}")
    @ApiOperation(value = "전류 이전 데이터 목록", notes = "날짜와 id 받아 전류 이전 데이러 목록을 반환")
    public ListResult<FormerDataResponse> getElectricCurrentList(HttpServletRequest httpServletRequest,
                                                                 @PathVariable Long id,
                                                                 FormerDataRequest request) {
        try {
            List<FormerDataResponse> result = formerDataService.getElectricCurrentList(request.getStartDate(), request.getEndDate(), id);
            return  responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @LoginCheck
    @GetMapping("/temperature/list/{id}")
    @ApiOperation(value = "온도 이전 데이터 목록", notes = "날짜와 id를 받아 온도 이전 데이러 목록을 반환")
    public ListResult<FormerDataResponse>  getTemperatureList(HttpServletRequest httpServletRequest,
                                                              @PathVariable Long id,
                                                              FormerDataRequest request) {
        try {
            List<FormerDataResponse> result = formerDataService.getTemperatureList(request.getStartDate(), request.getEndDate(), id);
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @LoginCheck
    @GetMapping("/voltage/list/{id}")
    @ApiOperation(value = "전압 이전 데이터 목록", notes = "날짜와 id를 받아 전압 이전 데이러 목록을 반환")
    public ListResult<FormerDataResponse>  getVoltageList(HttpServletRequest httpServletRequest,
                                                          @PathVariable Long id,
                                                          FormerDataRequest request) {
        try {
            List<FormerDataResponse> result = formerDataService.getVoltageList(request.getStartDate(), request.getEndDate(), id);
            return responseService.listResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }
}