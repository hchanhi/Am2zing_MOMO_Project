package com.momo.place;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.momo.board.BoardRepository;
import com.momo.board.BoardService;
import com.momo.domain.Board;
import com.momo.domain.Place;




@Controller
public class PlaceController {
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@RequestMapping("/place")
	public String findAllPlace(Model model) {
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
	
	@PostMapping("/placeList/{boardNum}")
	public String placeList(@PathVariable("boardNum") int boardNum, @RequestBody Map<String,String> map, Model model) {
		placeService.update(map);
		List<Place> placeList = placeService.getPlaceList(boardNum);
		model.addAttribute("places", placeList);
		return "Place/placeList";
	}
	
	@DeleteMapping("/deletePlace/{placeNum}")
	public String delete(@PathVariable("placeNum") int placeNum, @RequestParam int boardNum, Model model) {
		placeService.delete((long) placeNum);
		List<Place> placeList = placeService.getPlaceList(boardNum);
		model.addAttribute("places", placeList);
		return "Place/placeList";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "Board/addPlace";
	}
	
	 @PostMapping("/upload")
	 public String upload(@RequestParam("file1") MultipartFile multi, HttpServletRequest request, 
			 HttpServletResponse response, Model model) {
		 return placeService.upload(multi, request, response, model);
	 }
}
