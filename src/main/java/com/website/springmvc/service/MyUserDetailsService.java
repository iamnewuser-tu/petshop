package com.website.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.website.springmvc.entities.UserRole;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.website.springmvc.entities.User user = userService.getUserByUserName(username);
		System.out.println("UserInfo= " + user);

		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		Long id = user.getId();
		
		// [USER,ADMIN,..]
		List<UserRole> roles = userRoleService.getUserRoles(id);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		if (roles != null) {
			for (UserRole role : roles) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
				grantList.add(authority);
				System.out.println(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(user.getUserName(), //
				user.getPassWord(), grantList);

		return userDetails;
	}
}
