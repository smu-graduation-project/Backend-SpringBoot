package com.graduatioinProject.sensorMonitoring.productData.battery.controller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryRequest;
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

    //@ApiOperation(value = "배터리 추가", notes = "배터리 데이터 추가")
    @PostMapping("/upload/image/{id}")
    // https://kim-jong-hyun.tistory.com/78?category=910543 나중에 AWS에 올리면, S3로 변경
    public CommonResult setBattery(HttpServletRequest httpServletRequest,
                                   @PathVariable Long id,
                                   @RequestParam("image") MultipartFile image) {

        batteryService.uploadImage(image, id);
        return responseService.successResult();
    }

    @ApiOperation(value = "배터리 추가", notes = "배터리 데이터 추가")
    @PostMapping("/add")
    public CommonResult addBattery(HttpServletRequest httpServletRequest,
                                   @RequestBody BatteryRequest request) {
        batteryService.setBattery(request.toEntity());
        return responseService.successResult();
    }

    @ResponseBody
    @GetMapping("/image/{id}")
    public UrlResource getImage(HttpServletRequest httpServletRequest,
                                 @PathVariable Long id) throws MalformedURLException {
        String imgaeUrl = batteryService.getBattery(id).getImageUrl();
        return new UrlResource("file:" + imgaeUrl);
    }
}
