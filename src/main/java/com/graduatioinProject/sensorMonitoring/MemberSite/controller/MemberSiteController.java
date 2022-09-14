package com.graduatioinProject.sensorMonitoring.MemberSite.controller;

import com.graduatioinProject.sensorMonitoring.MemberSite.dto.MemberSiteRequest;
import com.graduatioinProject.sensorMonitoring.MemberSite.service.MemberSiteService;
import com.graduatioinProject.sensorMonitoring.baseUtil.aop.LoginCheckAdmin;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/09/13
 */

@Slf4j
@Api(tags = "01-1. Member 권한추가")
@CrossOrigin
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class MemberSiteController {
    private final MemberSiteService memberSiteService;
    private final ResponseService responseService;

    @LoginCheckAdmin
    @ApiOperation(value = "사이트 권한 추가", notes = "사이트와 유저의 정보를 받아 Site 권한 추가")
    @PostMapping("/add")
    public CommonResult addSite(HttpServletRequest httpServletRequest,
                                @RequestBody MemberSiteRequest request) {
        log.info("add MemberSite");
        memberSiteService.save(request);
        return responseService.successResult();
    }

    @LoginCheckAdmin
    @ApiOperation(value = "사이트 권한 삭", notes = "사이트와 유저의 정보를 받아 Site 권한 삭제")
    @PostMapping("/delete")
    public CommonResult deleteSite(HttpServletRequest httpServletRequest,
                                @RequestBody MemberSiteRequest request) {
        log.info("add MemberSite");
        memberSiteService.delete(request);
        return responseService.successResult();
    }

}
