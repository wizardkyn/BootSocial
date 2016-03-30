package com.example.web.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.example.web.domain.SignUpForm;

@Controller
public class SignupController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(ModelMap model,
			@AuthenticationPrincipal SocialUserDetails userDetail
			,WebRequest request) {
		
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		if (connection != null) {
			UserProfile socialMediaProfile = connection.fetchUserProfile();
		}
		
		String userId = (userDetail != null)? userDetail.getUsername():"";
		System.out.println("userId " + userId);

		return "/login/signup.jsp";	
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signupProc(ModelMap model
			,@ModelAttribute("form") SignUpForm form) {
		
		return "redirect:/login.do";
	}
}