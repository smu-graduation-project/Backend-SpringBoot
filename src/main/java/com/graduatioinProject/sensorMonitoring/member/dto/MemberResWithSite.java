package com.graduatioinProject.sensorMonitoring.member.dto;

import com.graduatioinProject.sensorMonitoring.productData.site.dto.SiteResponse;
import com.graduatioinProject.sensorMonitoring.productData.site.entity.Site;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/07/06
 */

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberResWithSite {
    private Long userSeq;
    private String username;
    private String role;
    private String employeeNumber;
    private String phoneNumber;
    private String realname;
    private String signupType;
    private String createDate;
    private String updateDate;
    private String activateYn;
    private List<SiteResponse> siteResponses;
}
