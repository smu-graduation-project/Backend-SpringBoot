package com.graduatioinProject.sensorMonitoring.productData.site.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.baseUtil.service.ResponseService;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.graduatioinProject.sensorMonitoring.productData.site.repository.SiteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */

@Slf4j
@Service
@AllArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;

    public Site setSite(Site site) {
        return siteRepository.save(site);
    }

    public SiteResponse getSite(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new BussinessException(ExMessage.SITE_ERROR_NOT_FOUND.getMessage()))
                .toResponse();
    }
}