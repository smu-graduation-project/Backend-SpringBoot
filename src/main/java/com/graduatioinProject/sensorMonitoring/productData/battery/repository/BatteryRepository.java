package com.graduatioinProject.sensorMonitoring.productData.battery.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/01
 */
public interface BatteryRepository extends JpaRepository<Battery, Long> {
}
