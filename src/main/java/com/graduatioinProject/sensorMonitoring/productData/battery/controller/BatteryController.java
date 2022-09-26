package com.graduatioinProject.sensorMonitoring.productData.battery.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheck;
import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheckAdmin;
import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.SiteUser;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.member.dto.Role;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryRequest;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
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

    private final JwtService jwtService;
    private final MemberService memberService;
    private final BatteryService batteryService;
    private final SiteService siteService;
    private final ResponseService responseService;
    private final AwsS3Service awsS3Service;

    @LoginCheckAdmin
    @ApiOperation(value = "배터리 추가", notes = "배터리 데이터 추가")
    @PostMapping("/add")
    public CommonResult addBattery(HttpServletRequest httpServletRequest,
                                   @RequestBody BatteryRequest batteryRequest,
                                   @RequestParam Long siteId) {
        BatteryResponse batteryResponse = batteryService.save(batteryRequest, siteId);
        return responseService.singleResult(batteryResponse);
    }

    @LoginCheckAdmin
    @ApiOperation(value = "배터리 수정", notes = "배터리 데이터 수정")
    @PutMapping("/update/{siteId}/{batteryId}")
    public CommonResult updateBattery(HttpServletRequest httpServletRequest,
                                   @RequestBody BatteryRequest batteryRequest,
                                   @PathVariable Long siteId,
                                   @PathVariable Long batteryId) {
        batteryService.update(batteryRequest, siteId, batteryId);
        return responseService.successResult();
    }

    @LoginCheckAdmin
    @ApiOperation(value = "배터리 이미지 추가", notes = "배터리 이미지 추가")
    @PostMapping("/upload/image/{batteryId}")
    public CommonResult setBatteryImage(HttpServletRequest httpServletRequest,
                                   @PathVariable Long batteryId,
                                   @RequestParam("image") MultipartFile image) {

        String imgUrl = awsS3Service.uploadFileV1(image);
        batteryService.addImage(imgUrl, batteryId);
        return responseService.successResult();

    }

    @SiteUser
    @ApiOperation(value = "배터리 상세정보", notes = "배터리 id를 받아 해당하는 배터리 정보를 반환")
    @GetMapping("/detail/{batteryId}")
    public SingleResult<BatteryResponse> getBatteryById(HttpServletRequest httpServletRequest,
                                                        @PathVariable Long batteryId) {
        return responseService.singleResult(
                batteryService.findById(batteryId));
    }

    @SiteUser
    @ApiOperation(value = "배터리 리스트(siteId)", notes = "사이트 id를 받아 해당하는 노드의 정보를 반환")
    @GetMapping("/list/{siteId}")
    public ListResult<BatteryResponse> getAllNodeByBattery(HttpServletRequest httpServletRequest,
                                                           @PathVariable Long siteId) {

        // 해당 사이트에 유저 권한이 있느지 확인
        return responseService.listResult(
                siteService.findByIdWithBattery(siteId).getBatteryResponse());
    }

    @LoginCheck
    @ApiOperation(value = "배터리 리스트(member)", notes = "해당 아이디로 접근 가능한 모든 배터리 정보를 반환")
    @GetMapping("/all")
    public CommonResult getAllNode(HttpServletRequest httpServletRequest) {

        try {
//            String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
//            if (memberService.findByUsername(userName).getRole().equals(Role.ADMIN.getName())) {
//                return responseService.listResult(batteryService.findAll());
//            }
//
//            return responseService.listResult(
//                    batteryService.findAll()
//                            .stream()
//                            .filter(i -> batteryService.chekMemberAuthorityUser(userName, i.getId()))
//                            .collect(Collectors.toList()));
            return responseService.listResult(batteryService.findAll());
        } catch (Exception e){
            return responseService.failResult(ExMessage.DATA_ERROR_NOT_FOUND.getMessage());
        }
    }


    @LoginCheckAdmin
    @ApiOperation(value = "배터리 삭제", notes = "배터리 삭제")
    @DeleteMapping("/delete/{batteryId}")
    public CommonResult deleteBattery(HttpServletRequest httpServletRequest,
                                      @PathVariable Long batteryId) {
        batteryService.delete(batteryId);
        return responseService.successResult();
    }
}
