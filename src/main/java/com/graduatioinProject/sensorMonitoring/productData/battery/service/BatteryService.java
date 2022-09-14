package com.graduatioinProject.sensorMonitoring.productData.battery.service;

import com.graduatioinProject.sensorMonitoring.MemberSite.entity.MemberSite;
import com.graduatioinProject.sensorMonitoring.MemberSite.service.MemberSiteService;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Battery> findAll() {
        return batteryRepository.findAll();
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
        return batteryRepositoryCustom.findByIdWithSite(id);
    }
    @Transactional(rollbackFor = Exception.class)
    public void save(BatteryRequest batteryRequest, Long siteId) {
        Battery battery = batteryRequest.toEntity();
        battery.setSite(Site.builder().id(siteId).build());
        this.save(battery);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(BatteryRequest batteryRequest, Long siteId, Long batteryId) {
        Battery battery = batteryRequest.toEntity();
        battery.setId(batteryId);
        battery.setSite(Site.builder().id(siteId).build());
        this.save(battery);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addImage(String imgUrl, Long batteryId) {
        BatteryResponseWithSite batteryResponseWithSite = this.findByIdWithSite(batteryId);
        batteryResponseWithSite.setImageUrl(imgUrl);
        this.save(batteryResponseWithSite.toEntity());
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Battery battery) {
        Battery newBattery = batteryRepository.save(battery);
    }


    public Boolean chekMemberAuthorityUser(String userName, Long batteryId) {
        SiteResponse siteResponse = batteryRepository.findById(batteryId).orElseThrow().getSite().toResponse();
        List<Long> siteIdList = memberSiteService.getSiteIdList(userName);
        return siteIdList.contains(siteResponse.getId());
    }

}
