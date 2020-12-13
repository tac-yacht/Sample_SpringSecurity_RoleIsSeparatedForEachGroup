package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;

@Controller
@RequestMapping("/item")
public class ItemController {

	@RequestMapping(params = "new")
	public String create(Model model) {
		model.addAttribute("item", new Item());
		return "item/write";
	}

	@RequestMapping("/{itemId}/edit")
	public String edit(@PathVariable("itemId") String itemId) {
		return "item/write";
	}
}
