package com.example;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.mvcMatchers("/").authenticated()
				.mvcMatchers("/item/**").authenticated()
				.mvcMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().denyAll()
			.and()
				.formLogin()
			;
	}
}
