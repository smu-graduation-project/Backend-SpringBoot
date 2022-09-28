package com.graduatioinProject.sensorMonitoring.productData.site.controller;

import com.graduatioinProject.sensorMonitoring.MemberSite.service.MemberSiteService;
import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheck;
import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheckAdmin;
import com.graduatioinProject.sensorMonitoring.baseUtil.Aop.SiteUser;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.member.dto.Role;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteRequest;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Slf4j
@Api(tags = "04. Site")
@CrossOrigin
@RestController
@RequestMapping("api/product/site")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;
    private final MemberSiteService memberSiteService;
    private final MemberService memberService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    @LoginCheckAdmin
    @ApiOperation(value = "사이트 추가", notes = "사이트관련 정보를 받아 Site 추가")
    @PostMapping("/add")
    public CommonResult addSite(HttpServletRequest httpServletRequest,
                                @RequestBody SiteRequest request) {
        log.info("addSite");
        siteService.save(request);
        return responseService.successResult();
    }

    @LoginCheckAdmin
    @ApiOperation(value = "사이트 수정", notes = "사이트관련 정보를 받아 Site 수정")
    @PutMapping("/update/{siteId}")
    public CommonResult updateSite(HttpServletRequest httpServletRequest,
                                   @PathVariable Long siteId,
                                   @RequestBody SiteRequest request) {

        siteService.update(request, siteId);
        return responseService.successResult();
    }

    @LoginCheckAdmin
    @ApiOperation(value = "사이트 삭제", notes = "사이트 id를 받아 사이트 삭제")
    @DeleteMapping("/delete/{siteId}")
    public CommonResult updateSite(HttpServletRequest httpServletRequest,
                                   @PathVariable Long siteId) {
        siteService.delete(siteId);
        return responseService.successResult();
    }

    @SiteUser
    @ApiOperation(value = "사이트 정보", notes = "사이트 id를 받아 해당하는 Site 상세정보를 반환")
    @GetMapping("/detail/{siteId}")
    public SingleResult<SiteResponse> getSite(HttpServletRequest httpServletRequest,
                                              @PathVariable Long siteId) {
        return responseService.singleResult(siteService.findById(siteId));
    }

    @LoginCheck
    @ApiOperation(value = "사이트 리스트", notes = "해당 아이디로 접근 가능한 모든 사이트 정보를 반환(admin인 경우 모든 site반환)")
    @GetMapping("/all")
    public CommonResult getAllNodeMember(HttpServletRequest httpServletRequest) {
        try {
            String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
            if (memberService.findByUsername(userName).getRole().equals(Role.ADMIN.getName())) {
                return responseService.listResult(siteService.findAll());
            }
            return responseService.listResult(
                    memberSiteService.getSiteList(userName));
//            return responseService.listResult(siteService.findAll());
        } catch (Exception e){
            return responseService.failResult(ExMessage.DATA_ERROR_NOT_FOUND.getMessage());
        }
    }
}
