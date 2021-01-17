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
				.mvcMatchers("/group/{groupId}/**").access("isAuthenticated() and @groupPermission.hasAuthority(principal, #groupId, 'ADMIN')")
				//accsess内の短絡評価で先に認証済みでないと、principal取ろうとすると死ぬ。特段ログインに回してはくれない
				//使用先メソッド側でOptionalにしても受け取れるけど、認証済みでないと落ちる。
				//今回は独自実装のhasAuthorityの引数に使うので、先に認証評価をする。
				.mvcMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().denyAll()
			.and()
				.formLogin()
			;
	}
}
