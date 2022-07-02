package com.graduatioinProject.sensorMonitoring.productData.site.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.graduatioinProject.sensorMonitoring.productData.site.repository.SiteRepository;
import com.graduatioinProject.sensorMonitoring.productData.site.repository.SiteRepositoryCustom;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    public Site findById(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.SITE_ERROR_NOT_FOUND.getMessage()));
    }

    public Site findByIdWithBattery(Long id) {
        return siteRepositoryCustom.findByIdWithBattery(id);
    }

    public void save(Site site) {
        siteRepository.save(site);
    }

    public Page<Site> getSitePage(int page) {
        return siteRepository.findAll(PageRequest.of(page, PAGINGSIZE));
    }

    public List<Site> getSiteList(int page) {
        Page<Site> pageSite = siteRepository.findAll(PageRequest.of(page, PAGINGSIZE));

        return pageSite.stream().collect(Collectors.toList());
    }

    public SiteResponse getSiteResponse(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.SITE_ERROR_NOT_FOUND.getMessage()))
                .toResponse();
    }

    public Boolean chekMemberAuthorityUser(Long memberId, Long siteId) {
        Site site = siteRepositoryCustom.findByIdWithMember(siteId);

//        Member member = memberService.findById(memberId);
//        return member.getSites().contains(site);

        // 임시
        return true;
    }

//    public Boolean checkMemberRole(Member member, Long siteId) {
//        Set<Member> members = siteRepositoryCustom.findByIdMemberRole(siteId).getMembers();
//        return members.contains(member);
//    }
}