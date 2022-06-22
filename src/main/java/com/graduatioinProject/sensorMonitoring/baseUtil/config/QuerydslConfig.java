package com.graduatioinProject.sensorMonitoring.baseUtil.config;

import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * @Author : Jeeseob
 * @CreateAt : 2022/06/21
 */
public class QuerydslConfig {
    @PersistenceContext
    EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
