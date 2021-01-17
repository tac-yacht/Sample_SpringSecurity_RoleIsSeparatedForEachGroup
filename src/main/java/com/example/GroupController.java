package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group/{groupId}")
public class GroupController {
	@ModelAttribute("groupId")
	public String setItem(@PathVariable("groupId") String groupId) {
		return groupId;
	}

	@GetMapping
	public String view() {
		return "group/index";
	}
}
