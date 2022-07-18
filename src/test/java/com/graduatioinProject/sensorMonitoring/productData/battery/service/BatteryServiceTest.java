package com.graduatioinProject.sensorMonitoring.productData.battery.service;

import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryRequest;
import com.graduatioinProject.sensorMonitoring.productData.battery.dto.BatteryResponse;
import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.battery.repository.BatteryRepository;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteRequest;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/07/17
 */

@SpringBootTest
class BatteryServiceTest {

    @Autowired
    private BatteryService batteryService;
    @Autowired
    private SiteService siteService;

    @Test
    void saveTest() {
        BatteryRequest battery = BatteryRequest.builder()
                .name("testName")
                .type("testType")
                .information("testInfo")
                .build();

        SiteRequest siteRequest = new SiteRequest();
        siteRequest.setGpsXPos(1);
        siteRequest.setGpsYPos(1);
        siteRequest.setInformation("asetd");
        siteRequest.setName("sdf");
        siteRequest.setType("test");
        siteService.save(siteRequest);
        batteryService.save(battery, 1L);

    }

}