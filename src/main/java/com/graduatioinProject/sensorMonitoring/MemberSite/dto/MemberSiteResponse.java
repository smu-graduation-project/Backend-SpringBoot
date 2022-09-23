package com.graduatioinProject.sensorMonitoring.MemberSite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/09/23
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSiteResponse {
    private String userName;
    private Long siteId;
}
