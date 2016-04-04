package com.example.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class KakaoController {

	@Autowired
	@Qualifier("kakaoRestTemplate")
	private RestOperations kakaoRestTemplate;
	
	@RequestMapping("/kakao/userinfo")
	public String userinfo(Model model,HttpServletRequest request) {
		
		String returnUrl = "redirect:/login.do?error";
		ObjectNode result = (ObjectNode)kakaoRestTemplate.getForObject(
				"https://kapi.kakao.com/v1/user/me",ObjectNode.class);

//		result {"id":120160638,"properties":{"nickname":"nickname","thumbnail_image":"","profile_image":""}}
		if (result != null) {
			final HttpSession session = request.getSession();
			final SecurityContext securityContext = SecurityContextHolder.getContext();
			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					result.findPath("id").asText(), "null", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_SOCIAL"));

			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			securityContext.setAuthentication(authentication);
			SecurityContextHolder.setContext(securityContext);

//			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
			
			returnUrl = "redirect:/";
		}

		return returnUrl;	
	}
}
