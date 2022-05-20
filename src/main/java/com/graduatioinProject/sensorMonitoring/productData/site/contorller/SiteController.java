package com.graduatioinProject.sensorMonitoring.productData.site.contorller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteRequest;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Api(tags = "06. Site")
@CrossOrigin
@RestController
@RequestMapping("api/product/site")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;
    private final ResponseService responseService;

    @PostMapping("/add")
    public CommonResult addSite(HttpServletRequest httpServletRequest,
                                @RequestBody SiteRequest request) {

        siteService.setSite(request.toEntity());
        return responseService.successResult();
    }

    @PutMapping("/update/{id}")
    public CommonResult updateSite(HttpServletRequest httpServletRequest,
                                   @PathVariable Long id,
                                   @RequestBody SiteRequest request) {

        Site site = request.toEntity();
        site.setId(id);
        siteService.setSite(site);
        return responseService.successResult();
    }

    @GetMapping("/{id}")
    public SingleResult<SiteResponse> getSite(HttpServletRequest httpServletRequest,
                                              @PathVariable Long id) {
        SiteResponse result = siteService.getSiteResponse(id);
        return responseService.singleResult(result);
    }
}
