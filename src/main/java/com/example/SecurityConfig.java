package com.example;

import static com.example.security.GroupPermissionExpressionBuilder.*;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.mvcMatchers("/").authenticated()
				.mvcMatchers(HttpMethod.GET, "/item/{groupId}/{itemId}").access(hasAnyGroupAuthority("groupId", "USER", "ADMIN"))
				.mvcMatchers(HttpMethod.GET, "/itemnew/{groupId}", "/item/{groupId}/{itemId}/edit").access(hasGroupAuthority("groupId", "ADMIN"))
				.mvcMatchers(HttpMethod.POST, "/itemnew/{groupId}","/item/{groupId}/**").access(hasGroupAuthority("groupId", "ADMIN"))
				.mvcMatchers("/group/{groupId}/**").access(hasAnyGroupAuthority("groupId", "USER", "ADMIN"))
				.mvcMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().denyAll()
			.and()
				.formLogin()
			;
	}
}
