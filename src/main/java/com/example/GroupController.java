package com.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;

@Controller
@RequestMapping("/group/{groupId}")
public class GroupController {

	static final Map<String, Item> groups = Collections.synchronizedMap(new HashMap<>());

//	@ModelAttribute("item")
//	public Item setItem(@PathVariable("itemId") String itemId) {
//		synchronized (items) {
//			Item item = items.get(itemId);
//			if(null==item) {
//				throw new RuntimeException("そのIDのitemがない");
//			}
//			return item;
//		}
//	}

	@GetMapping
	public String view() {
		return "group/index";
	}
}
