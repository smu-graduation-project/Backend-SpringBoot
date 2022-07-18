package com.graduatioinProject.sensorMonitoring.baseUtil.config;

import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/21
 */

@Configuration
public class QuerydslConfig {
    @PersistenceContext
    EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
