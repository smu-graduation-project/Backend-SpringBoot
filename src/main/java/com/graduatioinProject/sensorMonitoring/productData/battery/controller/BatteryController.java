package com.graduatioinProject.sensorMonitoring.productData.battery.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt.JwtProperties;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryRequest;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.AwsS3Service;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/01
 */
@Api(tags = "05. 배터리")
@RequestMapping("api/product/battery")
@CrossOrigin
@RequiredArgsConstructor
@RestController
@Slf4j
public class BatteryController {

    private final BatteryService batteryService;
    private final SiteService siteService;
    private final ResponseService responseService;
    private final AwsS3Service awsS3Service;

    @ApiOperation(value = "배터리 추가", notes = "배터리 데이터 추가")
    @PostMapping("/add")
    public CommonResult addBattery(HttpServletRequest httpServletRequest,
                                   @RequestBody BatteryRequest batteryRequest,
                                   @RequestParam Long siteId) {
        batteryService.save(batteryRequest, siteId);
        return responseService.successResult();
    }

    @ApiOperation(value = "배터리 수정", notes = "배터리 데이터 수정")
    @PostMapping("/update/{siteId}/{batteryId}")
    public CommonResult addBattery(HttpServletRequest httpServletRequest,
                                   @RequestBody BatteryRequest batteryRequest,
                                   @RequestParam Long siteId,
                                   @RequestParam Long batteryId) {
        batteryService.update(batteryRequest, siteId, batteryId);
        return responseService.successResult();
    }

    @ApiOperation(value = "배터리 이미지 추가", notes = "배터리 이미지 추가")
    @PostMapping("/upload/image/{batteryId}")
    public CommonResult setBattery(HttpServletRequest httpServletRequest,
                                   @PathVariable Long batteryId,
                                   @RequestParam("image") MultipartFile image) {

        String imgUrl = awsS3Service.uploadFileV1(image);
        batteryService.addImage(imgUrl, batteryId);
        return responseService.successResult();

    }

    @ApiOperation(value = "배터리 상세정보", notes = "배터리 id를 받아 해당하는 배터리 정보를 반환")
    @GetMapping("/detail/{id}")
    public SingleResult<BatteryResponse> getBatteryById(HttpServletRequest httpServletRequest,
                                                        @PathVariable Long id) {
        return responseService.singleResult(
                batteryService.findById(id));
    }

    @ApiOperation(value = "배터리 리스트(siteId)", notes = "사이트 id를 받아 해당하는 노드의 정보를 반환")
    @GetMapping("/list/{siteId}")
    public ListResult<BatteryResponse> getAllNodeByBattery(HttpServletRequest httpServletRequest,
                                                           @PathVariable Long siteId) {

        // 해당 사이트에 유저 권한이 있느지 확인
        return responseService.listResult(
                siteService.findByIdWithBattery(siteId).getBatteryResponse());
    }

    @ApiOperation(value = "배터리 리스트(member)", notes = "해당 아이디로 접근 가능한 모든 배터리 정보를 반환")
    @GetMapping("/all")
    public ListResult<BatteryResponse> getAllNode(HttpServletRequest httpServletRequest) {

        String userName = httpServletRequest.getHeader(JwtProperties.USERNAME);
        return responseService.listResult(
                batteryService.findAll()
                        .stream()
                        .filter(i -> batteryService.chekMemberAuthorityUser(userName, i.getId()))
                        .map(Battery::toResponse)
                        .collect(Collectors.toList()));
    }
}
