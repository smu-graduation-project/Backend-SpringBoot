package com.graduatioinProject.sensorMonitoring.productData.site.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteRequest;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponseWithBattery;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.graduatioinProject.sensorMonitoring.productData.site.repository.SiteRepository;
import com.graduatioinProject.sensorMonitoring.productData.site.repository.SiteRepositoryCustom;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Slf4j
@Service
@AllArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;
    private final SiteRepositoryCustom siteRepositoryCustom;

    public List<SiteResponse> findAll() {
        return siteRepository.findAll().stream().map(Site::toResponse).collect(Collectors.toList());
    }

    public SiteResponse findById(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.SITE_ERROR_NOT_FOUND.getMessage()))
                .toResponse();
    }

    public SiteResponseWithBattery findByIdWithBattery(Long id) {
        return siteRepositoryCustom.findByIdWithBattery(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(SiteRequest siteRequest) {
        Site site = siteRequest.toEntity();
        siteRepository.save(site);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SiteRequest siteRequest, Long id) {
        this.findById(id);
        // 해당 id가 존재하는지 확인
        Site site = siteRequest.toEntity();
        site.setId(id);
        siteRepository.save(site);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long siteId) {
        siteRepository.deleteById(siteId);
    }
}