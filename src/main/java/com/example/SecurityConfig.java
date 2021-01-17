package com.example;

import static com.example.security.GroupPermissionExpressionBuilder.*;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.mvcMatchers("/").authenticated()
				.mvcMatchers("/itemnew", "/item/**").authenticated()
				.mvcMatchers("/group/{groupId}/**").access(hasGroupAuthority("groupId", "ADMIN"))
				.mvcMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().denyAll()
			.and()
				.formLogin()
			;
	}
}
