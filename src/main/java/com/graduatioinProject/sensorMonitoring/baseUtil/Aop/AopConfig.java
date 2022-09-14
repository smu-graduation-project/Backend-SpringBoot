package com.graduatioinProject.sensorMonitoring.baseUtil.aop;

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
    private final SiteService siteService;
    private final BatteryService batteryService;
    private final NodeService nodeService;
    private final JwtService jwtService;
    private final MemberSiteService memberSiteService;

    // PointCut : 적용할 지점 또는 범위 선택
    // @Pointcut("execution(public * com.graduatioinProject.sensorMonitoring.productData.site.controller..*(..))")
    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.aop.SiteUser)")
    private void checkSiteUser() { }

    // @Pointcut("execution(public * com.graduatioinProject.sensorMonitoring.productData.battery.controller..*(..))")
    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.aop.BatteryUser)")
    private void checkBatteryUser() { }
    // @Pointcut("execution(public * com.graduatioinProject.sensorMonitoring.productData.node.controller..*(..))")
    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.aop.NodeUser)")
    private void checkNodeUser() { }

    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.aop.LoginCheck)")
    private void loginCheckUser() { }

    @Pointcut("@annotation(com.graduatioinProject.sensorMonitoring.baseUtil.aop.LoginCheckAdmin)")
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

    // @Before("checkSiteUser() && args(siteId)")
    @Around("checkSiteUser()")
    public Object checkSiteAuthorityUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check Site User");

        Object[] objects = joinPoint.getArgs();
        Long siteId = (Long) objects[1];
        Optional<HttpServletRequest> servletRequest =
                Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        HttpServletRequest httpServletRequest = servletRequest.get();
        String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
        log.info("userName = " + userName);

        List<Long> siteList = memberSiteService.getSiteIdList(userName);
        if (siteList.contains(siteId)) {
            return joinPoint.proceed();
        }
        else {
            throw new BussinessException(ExMessage.NO_AUTHORITY.getMessage());
        }
    }

    @Around("checkSiteAdmin()")
    public Object checkSiteAuthorityAdmin(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check Site Admin");

        Object[] objects = joinPoint.getArgs();
        Long siteId = (Long) objects[1];
        Optional<HttpServletRequest> servletRequest =
                Optional.of(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        HttpServletRequest httpServletRequest = servletRequest.get();
        String userName = jwtService.decode(httpServletRequest.getHeader("Authorization"));
        log.info("userName = " + userName);

        MemberRes memberRes = memberService.findByUsername(userName);
        if (memberRes.getRole().equals(Role.ADMIN.getName())) {
            List<Long> siteList = memberSiteService.getSiteIdList(userName);
            if (siteList.contains(siteId)) {
                return joinPoint.proceed();
            }
        }
        throw new BussinessException(ExMessage.NO_AUTHORITY.getMessage());

    }


    @Around("checkBatteryUser()")
    public Object checkBatteryAuthorityUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check Battery User");
//        HttpServletRequest request = null;
//        Long id = null;
//
//        Object[] parms = joinPoint.getArgs();
//
//        for (Object parm : parms) {
//            if (parm.equals("id")) {
//                id = (Long) parm;
//            }
//            if (parm.equals("httpServletRequest")) {
//                request = (HttpServletRequest) parm;
//            }
//        }

//        String memberId  = Objects.requireNonNull(request).getHeader(JwtProperties.ID);
//        if (!batteryService.chekMemberAuthorityUser(memberId, id)) {
//            throw new BussinessException(ExMessage.NO_AUTHORITY.getMessage());
//        }
        return joinPoint.proceed();
    }

    @Around("checkNodeUser()")
    public Object checkNodeAuthorityUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Check Node User");
//        HttpServletRequest request = null;
//        Long id = null;
//
//        Object[] parms = joinPoint.getArgs();
//
//        for (Object parm : parms) {
//            if (parm.equals("id")) {
//                id = (Long) parm;
//            }
//            if (parm.equals("httpServletRequest")) {
//                request = (HttpServletRequest) parm;
//            }
//        }

//        String memberId  = Objects.requireNonNull(request).getHeader(JwtProperties.ID);
//        if (!nodeService.chekMemberAuthorityUser(memberId, id)) {
//            throw new BussinessException(ExMessage.NO_AUTHORITY.getMessage());
//        }
        return joinPoint.proceed();
    }
}