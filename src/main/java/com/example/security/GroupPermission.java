package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GroupPermission {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(GroupPermission.class);

	public boolean hasAuthority(CustomUser user, String groupId) {
//		auth.getPrincipal()
		log.info("user: {}", user);
		log.info("groupId: {}", groupId);
		return true;
	}
}
