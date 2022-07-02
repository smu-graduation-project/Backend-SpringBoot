package com.graduatioinProject.sensorMonitoring.productData.site.contorller;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt.JwtProperties;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.CommonResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.ListResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.dto.SingleResult;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SitePageResponse;
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
import java.util.stream.Collectors;

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

    @ApiOperation(value = "사이트 추가", notes = "사이트관련 정보를 받아 Site 추가")
    @PostMapping("/add")
    public CommonResult addSite(HttpServletRequest httpServletRequest,
                                @RequestBody SiteRequest request) {

        siteService.save(request.toEntity());
        return responseService.successResult();
    }

    @ApiOperation(value = "사이트 수정", notes = "사이트관련 정보를 받아 Site 수정")
    @PutMapping("/update/{id}")
    public CommonResult updateSite(HttpServletRequest httpServletRequest,
                                   @PathVariable Long id,
                                   @RequestBody SiteRequest request) {

        Site site = request.toEntity();
        site.setId(id);
        siteService.save(site);
        return responseService.successResult();
    }

    @ApiOperation(value = "사이트 정보", notes = "사이트 id를 받아 해당하는 Site 상세정보를 반환")
    @GetMapping("/detail/{id}")
    public SingleResult<SiteResponse> getSite(HttpServletRequest httpServletRequest,
                                              @PathVariable Long id) {
        return responseService.singleResult(siteService.findById(id).toResponse());
    }

    @ApiOperation(value = "사이트 리스트(member + paging)", notes = "사이트 정보 List")
    @GetMapping("/list/{page}")
    public ListResult<SiteResponse> getSiteList(HttpServletRequest httpServletRequest,
                                                      @PathVariable int page) {
        Long memberId = Long.valueOf(httpServletRequest.getHeader(JwtProperties.ID));

        return responseService.listResult(
                siteService.getSiteList(page)
                        .stream()
                        .filter(i -> siteService.chekMemberAuthorityUser(memberId, i.getId()))
                        .map(Site::toResponse)
                        .collect(Collectors.toList()));
    }

    @ApiOperation(value = "사이트 리스트(member)", notes = "해당 아이디로 접근 가능한 모든 사이트 정보를 반환")
    @GetMapping("/all")
    public ListResult<SiteResponse> getAllNode(HttpServletRequest httpServletRequest) {
        Long memberId = Long.valueOf(httpServletRequest.getHeader(JwtProperties.ID));

        return responseService.listResult(
                siteService.findAll()
                        .stream()
                        .filter(i -> siteService.chekMemberAuthorityUser(memberId, i.getId()))
                        .map(Site::toResponse)
                        .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Site 페이지 정보", notes = "사이트 paging 정보")
    @GetMapping("/paging/{page}")
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
