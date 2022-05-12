package com.graduatioinProject.sensorMonitoring.baseUtil.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/image/**"); // /image/** 있는 모든 파일들은 시큐리티 적용을 무시한다.
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); // 정적인 리소스들에 대해서 시큐리티 적용 무시.
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.cors().disable()
				.authorizeRequests()
				.anyRequest() // 모든 요청에 대해서 허용하라.
				.permitAll().and().logout().logoutSuccessUrl("/"); // 로그아웃에 대해서 성공하면 "/"로 이동
		http.csrf().disable()
				.cors().disable()
				.headers().frameOptions().disable();
	}
}
