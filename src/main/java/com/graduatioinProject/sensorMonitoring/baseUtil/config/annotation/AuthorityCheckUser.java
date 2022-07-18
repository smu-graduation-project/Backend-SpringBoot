package com.graduatioinProject.sensorMonitoring.baseUtil.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/05/20
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthorityCheckUser {
}
