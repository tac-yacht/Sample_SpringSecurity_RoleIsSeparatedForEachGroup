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
				.mvcMatchers("/itemnew", "/item/**").authenticated()
				.mvcMatchers("/group/{groupId}/**").access("isAuthenticated() and @groupPermission.hasAuthority(principal, #groupId)")
				//先行評価で認証済みでないと、principal取るときに死ぬ特段ログインに回してはくれない
				.mvcMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().denyAll()
			.and()
				.formLogin()
			;
	}
}
