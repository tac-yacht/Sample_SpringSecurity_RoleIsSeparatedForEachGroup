package com.example.security;

import static com.example.security.ServiceNames.*;

import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service(GROUP_PERMISSION)
public class GroupPermission {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(GroupPermission.class);

	public boolean hasAuthority(CustomUser user, String groupId, String authority) {
		return hasAuthority(user.getSingleGroupAuthorities(groupId), authority);
	}

	public boolean hasAnyAuthority(CustomUser user, String groupId, String ... authorities) {
		return hasAnyAuthority(user.getSingleGroupAuthorities(groupId), authorities);
	}

	//ここから↓SecurityExpressionRootを参考に改造

	//セットだけ持ち回すように改造
	public final boolean hasAuthority(Collection<GrantedAuthority> target, String authority) {
		return hasAnyAuthority(target, authority);
	}

	//セットだけ持ち回すように改造
	public final boolean hasAnyAuthority(Collection<GrantedAuthority> target, String ... authorities) {
		return hasAnyAuthorityName(target, null, authorities);
	}

	//セットだけ持ち回すように改造
	private boolean hasAnyAuthorityName(Collection<GrantedAuthority> target, String prefix, String... roles) {
		Set<String> roleSet = getAuthoritySet(target);

		for (String role : roles) {
			String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
			if (roleSet.contains(defaultedRole)) {
				return true;
			}
		}

		return false;
	}

	//がっつり改造
	private Set<String> getAuthoritySet(Collection<GrantedAuthority> target) {
		return AuthorityUtils.authorityListToSet(target);
	}

	//丸ごとコピー
	private static String getRoleWithDefaultPrefix(String defaultRolePrefix, String role) {
		if (role == null) {
			return role;
		}
		if (defaultRolePrefix == null || defaultRolePrefix.length() == 0) {
			return role;
		}
		if (role.startsWith(defaultRolePrefix)) {
			return role;
		}
		return defaultRolePrefix + role;
	}
}
