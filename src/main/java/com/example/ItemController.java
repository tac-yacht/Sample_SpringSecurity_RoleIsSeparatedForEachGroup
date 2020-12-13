package com.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;

@Controller
@RequestMapping("/item/{itemId}")
public class ItemController {

	static final Map<String, Item> items = Collections.synchronizedMap(new HashMap<>());

	@GetMapping("edit")
	public String edit(@PathVariable("itemId") String itemId, Model model) {
		synchronized (items) {
			Item item = items.get(itemId);
			if(null==item) {
				throw new RuntimeException("そのIDのitemがない");
			}
			model.addAttribute("item", item);
		}
		return "item/write";
	}
}
