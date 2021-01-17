package com.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;

@Controller
@RequestMapping("/item/{groupId}/{itemId}")
public class ItemController {

	static final Map<String, Item> items = Collections.synchronizedMap(new HashMap<>());

	@ModelAttribute("item")
	public Item setItem(
		@PathVariable("groupId") String groupId,
		@PathVariable("itemId") String itemId
	) {
		synchronized (items) {
			Item item = items.get(itemId);
			if(null==item) {
				throw new RuntimeException("そのIDのitemがない");
			}
			if(!groupId.equals(item.getGroupId())) {
				throw new IllegalStateException("そのIDのitemに保持しているグループが一致しない");
			}
			return item;
		}
	}

	@GetMapping
	public String view() {
		return "item/read";
	}

	@GetMapping("edit")
	public String edit() {
		return "item/write";
	}
}
