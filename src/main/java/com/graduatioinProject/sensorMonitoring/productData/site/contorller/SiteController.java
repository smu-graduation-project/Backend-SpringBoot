package com.graduatioinProject.sensorMonitoring.productData.site.contorller;

import com.graduatioinProject.sensorMonitoring.baseUtil.annotation.LoginCheck;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SitePageResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SitePagingResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteRequest;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Api(tags = "04. Site")
@CrossOrigin
@RestController
@RequestMapping("api/product/site")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;
    private final ResponseService responseService;

    @LoginCheck
    @ApiOperation(value = "사이트 추가", notes = "사이트관련 정보를 받아 Site 추가")
    @PostMapping("/add")
    public CommonResult addSite(HttpServletRequest httpServletRequest,
                                @RequestBody SiteRequest request) {

        siteService.setSite(request.toEntity());
        return responseService.successResult();
    }

    @LoginCheck
    @ApiOperation(value = "사이트 수정", notes = "사이트관련 정보를 받아 Site 수정")
    @PutMapping("/update/{id}")
    public CommonResult updateSite(HttpServletRequest httpServletRequest,
                                   @PathVariable Long id,
                                   @RequestBody SiteRequest request) {

        Site site = request.toEntity();
        site.setId(id);
        siteService.setSite(site);
        return responseService.successResult();
    }

    @LoginCheck
    @ApiOperation(value = "사이트 정보", notes = "사이트 id를 받아 해당하는 Site 상세정보를 반환")
    @GetMapping("/{id}")
    public SingleResult<SiteResponse> getSite(HttpServletRequest httpServletRequest,
                                              @PathVariable Long id) {
        SiteResponse result = siteService.getSiteResponse(id);
        return responseService.singleResult(result);
    }

    @LoginCheck
    @ApiOperation(value = "사이트 List", notes = "사이트 정보 List")
    @GetMapping("/list/{page}")
    public ListResult<SitePagingResponse> getSiteList(HttpServletRequest httpServletRequest,
                                                      @PathVariable int page) {
        List<SitePagingResponse> siteList = siteService.getSiteList(page);
        return responseService.listResult(siteList);
    }

    @LoginCheck
    @ApiOperation(value = "사이트 paing", notes = "사이트 paging 정보")
    @GetMapping("/list/{page}")
    public SingleResult<SitePageResponse> getSitePage(HttpServletRequest httpServletRequest,
                                                      @PathVariable int page) {
        Page<Site> sitePage = siteService.getSitePage(page);

        SitePageResponse response = SitePageResponse
                .builder()
                .totalElements(sitePage.getTotalElements())
                .totalPages(sitePage.getTotalPages())
                .numberOfElements(sitePage.getNumberOfElements())
                .size(sitePage.getSize())
                .build();

        return responseService.singleResult(response);

    }

}
