package com.graduatioinProject.sensorMonitoring.productData.site.repository;

import com.graduatioinProject.sensorMonitoring.productData.battery.entity.Battery;
import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/12
 */
@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
}
