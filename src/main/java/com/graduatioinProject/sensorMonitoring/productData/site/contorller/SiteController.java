package com.graduatioinProject.sensorMonitoring.productData.site.contorller;

import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteRequest;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Api(tags = "06. Site")
@RestController
@RequestMapping("api/product/site")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;
    private final ResponseService responseService;

    @PostMapping("/add")
    public CommonResult addSite(@RequestBody SiteRequest request) {

        siteService.setSite(request.toEntity());
        /**
         * user 권한여부 추가
         */
        return responseService.successResult();
    }

    @GetMapping("/{id}")
    public SingleResult<SiteResponse> getSite(@PathVariable Long id) {
        SiteResponse result = siteService.getSite(id);
        return responseService.singleResult(result);
    }
}
