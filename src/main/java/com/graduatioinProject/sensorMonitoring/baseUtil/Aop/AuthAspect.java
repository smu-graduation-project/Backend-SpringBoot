package com.graduatioinProject.sensorMonitoring.baseUtil.Aop;

import com.graduatioinProject.sensorMonitoring.baseUtil.config.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/22
 */

@RequiredArgsConstructor
@Aspect     // AOP Aspect
@Component
public class AuthAspect {

    private static final String AUTHORIZATION = "accessToken";

    private final JwtService jwtService;
    private final HttpServletRequest httpServletRequest;


}

