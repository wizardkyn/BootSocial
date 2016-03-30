package com.example.web.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

	private LoginService loginService;

	public SocialUserDetailsServiceImpl(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

		UserDetails userDetails = loginService.loadUserByUsername(userId);
		if (userDetails == null) throw new UsernameNotFoundException("Not Found ID : " + userId);
		
		return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	}
}
