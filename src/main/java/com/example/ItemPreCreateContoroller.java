package com.example;

import static com.example.ItemController.*;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.entity.Item;

@Controller
public class ItemPreCreateContoroller {

	@GetMapping("/itemnew")
	public String create(Model model) {
		model.addAttribute("item", new Item());
		return "item/write";
	}

	@PostMapping({"/item"/*新規作成*/, "/item/{itemId}"})
	public String write(@PathVariable("itemId") Optional<String> itemId, @ModelAttribute Item item, Model model) {
		itemId.ifPresent(id -> {
			if(!id.equals(item.getId())) {
				throw new RuntimeException("id変更不可");
			}
		});

		synchronized (items) {
			if(itemId.isPresent() ^ items.containsKey(item.getId())) {
				throw new RuntimeException("/pathにidがあるのにデータが無い or 新規なのにデータがある");
			}

			items.put(item.getId(), item);
		}

		return "redirect:" + MvcUriComponentsBuilder.fromMethodName(ItemController.class, "view")
			.build(item.getId())
		;
	}
}
