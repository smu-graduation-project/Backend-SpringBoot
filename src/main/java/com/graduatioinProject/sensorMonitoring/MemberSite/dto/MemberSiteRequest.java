package com.graduatioinProject.sensorMonitoring.MemberSite.dto;

import com.graduatioinProject.sensorMonitoring.MemberSite.entity.MemberSite;
import com.graduatioinProject.sensorMonitoring.MemberSite.service.MemberSiteService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/09/14
 */

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberSiteRequest {
    private String userName;
    private Long siteId;
}

