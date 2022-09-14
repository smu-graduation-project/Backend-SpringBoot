package com.graduatioinProject.sensorMonitoring.MemberSite.service;

import com.graduatioinProject.sensorMonitoring.MemberSite.dto.MemberSiteRequest;
import com.graduatioinProject.sensorMonitoring.MemberSite.entity.MemberSite;
import com.graduatioinProject.sensorMonitoring.MemberSite.repository.MemberSiteRepository;
import com.graduatioinProject.sensorMonitoring.MemberSite.repository.MemberSiteRepositoryCustom;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/09/13
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberSiteService {
    private final MemberService memberService;
    private final SiteService siteService;
    private final MemberSiteRepository memberSiteRepository;
    private final MemberSiteRepositoryCustom memberSiteRepositoryCustom;

    public List<Long> getSiteIdList(String userName) {
        List<MemberSite> memberSiteList = memberSiteRepositoryCustom.getSiteIdList(userName);
        return memberSiteList.stream().map(i->i.getSite().getId()).collect(Collectors.toList());
    }

    public List<SiteResponse> getSiteList(String userName) {
        List<MemberSite> memberSiteList = memberSiteRepositoryCustom.getSiteIdList(userName);
        return memberSiteList.stream().map(i->i.getSite().toResponse()).collect(Collectors.toList());
    }

    public MemberSite save(MemberSiteRequest memberSiteRequest) {

        return memberSiteRepository.save(
                MemberSite.builder()
                        .member(memberService.findByUsername(memberSiteRequest.getUserName()).toEntity())
                        .site(siteService.findById(memberSiteRequest.getSiteId()).toEntity())
                        .build());
    }

    public void delete(MemberSiteRequest memberSiteRequest) {
        memberSiteRepository.delete(MemberSite.builder()
                .member(memberService.findByUsername(memberSiteRequest.getUserName()).toEntity())
                .site(siteService.findById(memberSiteRequest.getSiteId()).toEntity())
                .build());
    }
}
