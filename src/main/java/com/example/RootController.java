package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.CustomUser;

@Controller
public class RootController {

	@RequestMapping("/")
	public String root(@AuthenticationPrincipal CustomUser user) {
		return "root";
	}
}
