package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group/{groupId}")
public class GroupController {
	@GetMapping
	public String view() {
		return "group/index";
	}
}
