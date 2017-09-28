package com.testing.system.web.security;

import com.testing.system.entities.enums.Role;

/**
 * Representation of the user authorized in the system
 * <p>
 * The real {@link User} object isn't stored in session, because of possibility of
 * cache aging.
 * @author yuri
 *
 */
public class UserPrincipal {
	private String username;
	private Role role;
	private Long id;

	public UserPrincipal(String username, Role role, Long id) {
		this.username = username;
		this.role = role;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
