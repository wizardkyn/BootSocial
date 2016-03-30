package com.example.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class KakaoController {

	private RestOperations kakaoRestTemplate;
	
	@RequestMapping("/kakao/userinfo")
	public String userinfo(Model model) throws Exception {
		ObjectNode result = (ObjectNode)kakaoRestTemplate.getForObject(
				"https://kapi.kakao.com/v1/user/me",ObjectNode.class);

		System.out.println("result " + result);
		return "redirect:/signup";	
	}

	public void setKakaoRestTemplate(RestOperations kakaoRestTemplate) {
		this.kakaoRestTemplate = kakaoRestTemplate;
	}
}
