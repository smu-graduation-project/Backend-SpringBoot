package com.graduatioinProject.sensorMonitoring.productData.battery.service;

import com.graduatioinProject.sensorMonitoring.MemberSite.service.MemberSiteService;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryRequest;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithNode;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponseWithSite;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.repository.BatteryRepository;
import com.graduatioinProject.sensorMonitoring.productData.battery.repository.BatteryRepositoryCustom;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/01
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BatteryService {
    private final SiteService siteService;
    private final BatteryRepository batteryRepository;
    private final MemberSiteService memberSiteService;
    private final BatteryRepositoryCustom batteryRepositoryCustom;

    public List<BatteryResponse> findAll() {
        return batteryRepository.findAll().stream().map(Battery::toResponse).collect(Collectors.toList());
    }

    public BatteryResponse findById(Long id) {
        Optional<Battery> response = batteryRepository.findById(id);
        if (response.isPresent()) {
            return response.get().toResponse();
        }
        throw (new BussinessException(ExMessage.BATTERY_ERROR_NOT_FOUND.getMessage()));
    }

    public BatteryResponseWithNode findByIdWithNode(Long id) {
        return batteryRepositoryCustom.findByIdWithNode(id);
    }

    public BatteryResponseWithSite findByIdWithSite(Long id) {
        return batteryRepositoryCustom.findByIdWithSite(id).toResponseWithSite();
    }
    @Transactional(rollbackFor = Exception.class)
    public BatteryResponse save(BatteryRequest batteryRequest, Long siteId) {
        Battery battery = batteryRequest.toEntity();
        battery.setSite(Site.builder().id(siteId).build());
        return batteryRepository.save(battery).toResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(BatteryRequest batteryRequest, Long siteId, Long batteryId) {
        Battery battery = batteryRepositoryCustom.findByIdWithSite(batteryId);
        battery.setInformation(batteryRequest.getInformation());
        battery.setName(batteryRequest.getName());
        battery.setType(batteryRequest.getType());
        battery.setSite(siteService.findById(siteId).toEntity());
        batteryRepository.save(battery);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addImage(String imgUrl, Long batteryId) {
        BatteryResponseWithSite batteryResponseWithSite = this.findByIdWithSite(batteryId);
        batteryResponseWithSite.setImageUrl(imgUrl);
        this.save(batteryResponseWithSite.toEntity());
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Battery battery) {
        batteryRepository.save(battery);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long batteryId) {
        batteryRepository.deleteById(batteryId);
    }

    public Boolean chekMemberAuthorityUser(String userName, Long batteryId) {
        SiteResponse siteResponse = batteryRepository.findById(batteryId).orElseThrow().getSite().toResponse();
        List<Long> siteIdList = memberSiteService.getSiteIdList(userName);
        return siteIdList.contains(siteResponse.getId());
    }

}
