package com.graduatioinProject.sensorMonitoring.baseUtil.config;

import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.memberUtil.dto.MemberSessionDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/20
 */

@Aspect
@Component
public class AopConfig {

    @Before("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.annotation.LoginCheck)")
    private void loginCheck(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest httpServletRequest = (HttpServletRequest) args[0];

        HttpSession session = httpServletRequest.getSession();
        MemberSessionDto loginMember = (MemberSessionDto) session.getAttribute("member");

        if (loginMember == null) {
            throw (new BussinessException(ExMessage.SESSION_ERROR_MEMBER_NOT_FOUND.getMessage()));
        }
    }



    /**
     * 권한 체크 method추가
     */
}
