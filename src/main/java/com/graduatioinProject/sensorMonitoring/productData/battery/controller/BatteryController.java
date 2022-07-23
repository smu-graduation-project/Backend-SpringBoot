package com.graduatioinProject.sensorMonitoring.productData.battery.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt.JwtProperties;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryRequest;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.List;
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

//    @ApiOperation(value = "배터리 이미지 추가", notes = "배터리 임미지 추가")
//    @PostMapping("/upload/image/{id}")
//    // https://kim-jong-hyun.tistory.com/78?category=910543 나중에 AWS에 올리면, S3로 변경
//    public CommonResult setBattery(HttpServletRequest httpServletRequest,
//                                   @PathVariable Long id,
//                                   @RequestParam("image") MultipartFile image) {
//
//        batteryService.uploadImage(image, id);
//        return responseService.successResult();
//    }

    @ApiOperation(value = "배터리 추가", notes = "배터리 데이터 추가")
    @PostMapping("/add")
    public CommonResult addBattery(HttpServletRequest httpServletRequest,
                                   @RequestBody BatteryRequest batteryRequest,
                                   @RequestParam Long siteId) {
        batteryService.save(batteryRequest, siteId);
        return responseService.successResult();
    }
//
//    @ApiOperation(value = "배터리 이미지 상세", notes = "배터리 이미지 url")
//    @GetMapping("/image/{id}")
//    public UrlResource getImage(HttpServletRequest httpServletRequest,
//                                @PathVariable Long id) throws MalformedURLException {
//        String imgaeUrl = batteryService.findById(id).getImageUrl();
//        return new UrlResource("file:" + imgaeUrl);
//    }

    @ApiOperation(value = "배터리 상세정보", notes = "배터리 id를 받아 해당하는 배터리 정보를 반환")
    @GetMapping("/detail/{id}")
    public SingleResult<BatteryResponse> getBatteryById(HttpServletRequest httpServletRequest,
                                                        @PathVariable Long id) {
        return responseService.singleResult(
                batteryService.findById(id).toResponse());
    }

    @ApiOperation(value = "배터리 리스트(siteId)", notes = "사이트 id를 받아 해당하는 노드의 정보를 반환")
    @GetMapping("/list/{id}")
    public ListResult<BatteryResponse> getAllNodeByBattery(HttpServletRequest httpServletRequest,
                                                           @PathVariable Long id) {
        /**
         * 페이징을 적용해야할지 의문
         */
        List<Battery> batteryList = siteService.findByIdWithBattery(id).getBatteries();
        return responseService.listResult(
                batteryService.findAll()
                        .stream()
                        .filter(batteryList::contains)
                        .map(Battery::toResponse)
                        .collect(Collectors.toList()));
    }

    @ApiOperation(value = "배터리 리스트(member)", notes = "해당 아이디로 접근 가능한 모든 배터리 정보를 반환")
    @GetMapping("/all")
    public ListResult<BatteryResponse> getAllNode(HttpServletRequest httpServletRequest) {
        String userName = httpServletRequest.getHeader(JwtProperties.USERNAME);
        /**
         * 페이징을 적용해야할지 의문
         */
        return responseService.listResult(
                batteryService.findAll()
                        .stream()
                        .filter(i -> batteryService.chekMemberAuthorityUser(userName, i.getId()))
                        .map(Battery::toResponse)
                        .collect(Collectors.toList()));
    }
}
