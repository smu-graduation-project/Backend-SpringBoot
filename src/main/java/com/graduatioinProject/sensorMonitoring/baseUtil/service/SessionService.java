package com.graduatioinProject.sensorMonitoring.baseUtil.service;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/15
 */

@Service
public class SessionService {
    public MemberSessionDto checkMemberSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();

        if (session == null) {
            throw (new BussinessException(ExMessage.DATA_ERROR_SESSION_NOT_EXIST.getMessage()));
        }

        MemberSessionDto loginMember = (MemberSessionDto) session.getAttribute("member");

        if (loginMember == null) {
            throw (new BussinessException(ExMessage.DATA_ERROR_MEMBER_NOT_FOUND.getMessage()));
        }

        return loginMember;
    }
}
