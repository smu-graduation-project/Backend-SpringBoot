package com.graduatioinProject.sensorMonitoring.baseUtil.Aop;

import com.graduatioinProject.sensorMonitoring.MemberSite.service.MemberSiteService;
import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.BussinessException;
import com.graduatioinProject.sensorMonitoring.baseUtil.exception.ExMessage;
import com.graduatioinProject.sensorMonitoring.member.dto.MemberRes;
import com.graduatioinProject.sensorMonitoring.member.dto.Role;
import com.graduatioinProject.sensorMonitoring.member.service.MemberService;
import com.graduatioinProject.sensorMonitoring.productData.battery.service.BatteryService;
import com.graduatioinProject.sensorMonitoring.productData.node.service.NodeService;
import com.graduatioinProject.sensorMonitoring.productData.site.service.SiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/08/23
 */

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AopConfig {
    private final MemberService memberService;
    private final NodeService nodeService;
    private final JwtService jwtService;
    private final MemberSiteService memberSiteService;

    // PointCut : 적용할 지점 또는 범위 선택
    // @Pointcut("execution(public * com.graduatioinProject.sensorMonitoring.productData.site.controller..*(..))")
    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.Aop.SiteUser)")
    private void checkSiteUser() { }

    // @Pointcut("execution(public * com.graduatioinProject.sensorMonitoring.productData.battery.controller..*(..))")
    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.Aop.BatteryUser)")
    private void checkBatteryUser() { }
    // @Pointcut("execution(public * com.graduatioinProject.sensorMonitoring.productData.node.controller..*(..))")
    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.Aop.NodeUser)")
    private void checkNodeUser() { }

    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheck)")
    private void loginCheckUser() { }

    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.Aop.LoginCheckAdmin)")
    private void loginCheckAdmin() { }


    @Around("loginCheckUser()")
    public Object checkUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check User");

        Optional<HttpServletRequest> servletRequest =
                Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        HttpServletRequest httpServletRequest = servletRequest.get();
        String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
        memberService.findByUsername(userName);
        log.info("userName = " + userName);
        return joinPoint.proceed();
    }


    @Around("loginCheckAdmin()")
    public Object checkAdmin(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check Admin");

        Optional<HttpServletRequest> servletRequest =
                Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        HttpServletRequest httpServletRequest = servletRequest.get();
        String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
        MemberRes memberRes = memberService.findByUsername(userName);
        log.info("userName = " + userName);
        if(memberRes.getRole().equals(Role.ADMIN.getName())) {
            return joinPoint.proceed();
        }
        throw new BussinessException(ExMessage.NO_AUTHORITY.getMessage());
    }


    // @Before("checkSiteUser() && args(siteId)")
    @Around("checkSiteUser()")
    public Object checkSiteAuthorityUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check Site User");

        Object[] objects = joinPoint.getArgs();
        Long siteId = (Long) objects[1];
        log.info("site Id is " + siteId);
        Optional<HttpServletRequest> servletRequest =
                Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        HttpServletRequest httpServletRequest = servletRequest.get();
        String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
        log.info("userName = " + userName);

        MemberRes memberRes = memberService.findByUsername(userName);
        log.info("userName = " + userName);
        if(memberRes.getRole().equals(Role.ADMIN.getName())) {
            return joinPoint.proceed();
        }

        List<Long> siteList = memberSiteService.getSiteIdList(userName);
        if (siteList.contains(siteId)) {
            return joinPoint.proceed();
        }
        throw new BussinessException(ExMessage.NO_AUTHORITY.getMessage());

    }

    @Around("checkBatteryUser()")
    public Object checkBatteryAuthorityUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check Battery User");

        Object[] objects = joinPoint.getArgs();
        Long batteryId = (Long) objects[1];
        log.info("Battery Id is " + batteryId);
        Optional<HttpServletRequest> servletRequest =
                Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        HttpServletRequest httpServletRequest = servletRequest.get();
        String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
        log.info("userName = " + userName);

        MemberRes memberRes = memberService.findByUsername(userName);
        log.info("userName = " + userName);
        if(memberRes.getRole().equals(Role.ADMIN.getName())) {
            return joinPoint.proceed();
        }

        if (nodeService.chekMemberAuthorityUser(userName, batteryId)) {
            return joinPoint.proceed();
        }
        throw new BussinessException(ExMessage.NO_AUTHORITY.getMessage());
    }

    @Around("checkNodeUser()")
    public Object checkNodeAuthorityUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check Node User");

        Object[] objects = joinPoint.getArgs();
        Long nodeId = (Long) objects[1];

        log.info("node Id is " + nodeId);
        Optional<HttpServletRequest> servletRequest =
                Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        HttpServletRequest httpServletRequest = servletRequest.get();
        String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
        log.info("userName = " + userName);

        MemberRes memberRes = memberService.findByUsername(userName);
        log.info("userName = " + userName);
        if(memberRes.getRole().equals(Role.ADMIN.getName())) {
            return joinPoint.proceed();
        }

        if (nodeService.chekMemberAuthorityUser(userName, nodeId)) {
            return joinPoint.proceed();
        }
        throw new BussinessException(ExMessage.NO_AUTHORITY.getMessage());

    }
}