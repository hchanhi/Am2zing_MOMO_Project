package com.momo.place;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PlaceController {
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping("/place")
	public String findAllPlace(Model model) {
		System.out.println(this.placeService.findAll().toString());
		model.addAttribute("myplaces", this.placeService.findAll());
		return "/view/place";
	}
	

	@GetMapping("/test")
	public String MapTest() {
		return "view/test";
	}
	
	
	@GetMapping("/test02/{boardNum}")
	public String MapTest02(@PathVariable Integer boardNum, Model model) {
		model.addAttribute("myplaces", this.placeService.findByBoardNum(boardNum));
		return "view/test02"; 
	}
	
	
	
	@RequestMapping
	public String addCard(@RequestParam String placeTitle, 
							String placeLat, 
							String placeLng,
							String placeId, 
							String placeContent, 
							String memEmail,
							int boardNum, 
							Model model) {
		if(!Objects.isNull(boardNum)&& !placeTitle.isBlank()) {
			this.placeService.save(placeTitle, placeLat, placeLng, placeId, placeContent, memEmail, boardNum);
		}
		System.out.println(boardNum);
		return "/index";
	}
}
