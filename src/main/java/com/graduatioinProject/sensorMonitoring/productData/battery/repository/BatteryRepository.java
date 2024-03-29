package com.graduatioinProject.sensorMonitoring.productData.battery.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/01
 */

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
}
