package com.graduatioinProject.sensorMonitoring.baseUtil.config.Aop;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.jwt.JwtProperties;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/22
 */

@RequiredArgsConstructor
@Aspect     // AOP Aspect
@Component
@Slf4j
class CheckMemberAuthority {

    private final SiteService siteService;
    private final BatteryService batteryService;
    private final NodeService nodeService;

    @Before("@annotation(AuthorityCheckUser)")
    public void checkSiteUser(JoinPoint joinPoint) {
        HttpServletRequest request = null;
        Long siteId = null;

        Object[] parms = joinPoint.getArgs();

        for (Object parm : parms) {
            if (parm.equals("id")) {
                siteId = (Long) parm;
            }
            if (parm.equals("httpServletRequest")) {
                request = (HttpServletRequest) parm;
            }
        }

        String memberId = request.getHeader(JwtProperties.USERNAME);
        siteService.chekMemberAuthorityUser(memberId, siteId);
    }

    @Before("@annotation(AuthorityCheckUser)")
    public void checkBatteryUser(JoinPoint joinPoint) {
        HttpServletRequest request = null;
        Long batteryId = null;

        Object[] parms = joinPoint.getArgs();

        for (Object parm : parms) {
            if (parm.equals("id")) {
                batteryId = (Long) parm;
            }
            if (parm.equals("httpServletRequest")) {
                request = (HttpServletRequest) parm;
            }
        }

        String memberId  = request.getHeader(JwtProperties.ID);
        batteryService.chekMemberAuthorityUser(memberId, batteryId);
    }

    @Before("@annotation(AuthorityCheckUser)")
    public void checkNodeAuthorityUser(JoinPoint joinPoint) {

        HttpServletRequest request = null;
        Long nodeId = null;

        Object[] parms = joinPoint.getArgs();

        for (Object parm : parms) {
            if (parm.equals("id")) {
                nodeId = (Long) parm;
            }
            if (parm.equals("httpServletRequest")) {
                request = (HttpServletRequest) parm;
            }
        }

        String memberId = request.getHeader(JwtProperties.ID);
        if (!nodeService.chekMemberAuthorityUser(memberId, nodeId)) {
            throw new BussinessException("Authority Error");
        }
    }
}
