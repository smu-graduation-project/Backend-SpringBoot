package com.graduatioinProject.sensorMonitoring.productData.battery.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/07/17
 */

@SpringBootTest
class BatteryRepositoryTest {

    @Autowired
    private BatteryRepository batteryRepository;

    @Test
    void saveTest() {

        Battery battery = Battery.builder()
                .information("taest")
                .type("saet")
                .name("Setdsf")
                .build();

        batteryRepository.save(battery);
    }
}