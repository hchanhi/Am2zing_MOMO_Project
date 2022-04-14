package com.momo.place;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.momo.domain.Board;
import com.momo.domain.Member;


@Controller
public class PlaceController {
	@Autowired
	private PlaceService placeService;
	
	
	@RequestMapping
	public String addCard(@RequestParam String placeTitle, String placeLat, String placeLng,
			String placeId, String placeContent,Board board, Model model) {
		if(!Objects.isNull(placeTitle)&& !placeTitle.isBlank()) {
			this.placeService.save(placeTitle, placeLat, placeLng, placeId, placeContent, board);
		}
		return "/index";
	}
}
