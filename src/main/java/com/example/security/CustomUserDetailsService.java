package com.example.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final Map<String, CustomUser> dummyUsers;

	static {
		Map<String, CustomUser> temp = new HashMap<>();
		{
			Map<String, Collection<? extends GrantedAuthority>> groupAuthorities = new HashMap<>();
				groupAuthorities.put("groupA", Collections.singleton(new SimpleGrantedAuthority("USER")));
			temp.put("user1", new CustomUser("user1", "{noop}a", Collections.emptyList(), groupAuthorities));
		}
		dummyUsers = Collections.unmodifiableMap(temp);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(dummyUsers.get(username))
			.orElseThrow(()->new UsernameNotFoundException("dummy static map not fount."))
		;
	}

}
