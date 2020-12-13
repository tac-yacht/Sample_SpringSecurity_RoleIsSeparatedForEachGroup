package com.example.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

public class CustomUser extends User {

	private final Map<String, Set<GrantedAuthority>> groupAuthorities;

	public CustomUser(String username, String password,
		Collection<? extends GrantedAuthority> authorities,
		Map<String, Collection<? extends GrantedAuthority>> groupAuthorities
	) {
		super(username, password, authorities);
		this.groupAuthorities = Collections.unmodifiableMap(groupAuthorities.entrySet().stream()
			.map(entry -> new SimpleEntry<>(entry.getKey(), Collections.unmodifiableSet(sortAuthorities(entry.getValue()))))
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
	}

	public Map<String, Set<GrantedAuthority>> getGroupAuthorities() {
		return groupAuthorities;
	}

	public Collection<GrantedAuthority> getSingleGroupAuthorities(String group) {
		return Optional.ofNullable(groupAuthorities.get(group))
			.orElseGet(Collections::emptySet);
	}





	private static class SimpleEntry<K,V> implements Entry<K, V> {

		private K key;
		private V value;
		public SimpleEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return this.value;
		}

	}


	//copy from org.springframework.security.core.userdetails.User

	private static SortedSet<GrantedAuthority> sortAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
				new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority,
					"GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>,
			Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before adding it to
			// the set.
			// If the authority is null, it is a custom authority and should precede
			// others.
			if (g2.getAuthority() == null) {
				return -1;
			}

			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}
}
