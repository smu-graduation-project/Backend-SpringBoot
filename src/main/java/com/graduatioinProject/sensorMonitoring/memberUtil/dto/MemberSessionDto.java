package com.graduatioinProject.sensorMonitoring.memberUtil.dto;

import com.graduatioinProject.sensorMonitoring.member.entity.Member;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class MemberSessionDto implements Serializable {
    private String userId;
    private String password;

    public MemberSessionDto(Member member) {
        this.userId = member.getUserId();
        this.password = member.getPassword();
    }
}