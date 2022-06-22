package com.graduatioinProject.sensorMonitoring.productData.site.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SitePagingResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteRequest;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.graduatioinProject.sensorMonitoring.productData.site.repository.SiteRepository;
import com.graduatioinProject.sensorMonitoring.productData.site.repository.SiteRepositoryCustom;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Slf4j
@Service
@AllArgsConstructor
public class SiteService {
    private static final int PAGINGSIZE = 10;
    private final SiteRepository siteRepository;
    private final SiteRepositoryCustom siteRepositoryCustom;

    public void setSite(Site site) {
        siteRepository.save(site);
    }

    public Page<Site> getSitePage(int page) {
        return siteRepository.findAll(PageRequest.of(page, PAGINGSIZE));
    }

    public List<SitePagingResponse> getSiteList(int page) {
        Page<Site> pageSite = siteRepository.findAll(PageRequest.of(page, PAGINGSIZE));
        List<SitePagingResponse> pagingResponseList = new ArrayList<>();
        pageSite.getContent().forEach(i -> pagingResponseList.add(i.toPagingResponse()));

        return pagingResponseList;
    }

    public SiteResponse getSiteResponse(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.SITE_ERROR_NOT_FOUND.getMessage()))
                .toResponse();
    }

    public Site getSite(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.SITE_ERROR_NOT_FOUND.getMessage()));
    }

    public Boolean checkMemberRole(Member member, Long siteId) {
        Set<Member> members = siteRepositoryCustom.findByIdMemberRole(siteId).getMembers();
        return members.contains(member);
    }
}