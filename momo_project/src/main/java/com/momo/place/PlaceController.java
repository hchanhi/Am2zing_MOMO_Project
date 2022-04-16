package com.momo.place;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.domain.Place;



@Controller
public class PlaceController {
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private PlaceRepository placeRepository;
	
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
	
	
	@ResponseBody
	@PostMapping("/updatePlace")
	public ResponseEntity<?> update(@RequestBody Map<String,String> map) {
		//System.out.println(map.toString());
		Map<String, String> tempMap=new HashMap<>();
		//DB처리
		String placeTitle = map.get("placeTitle");
		String placeLat = map.get("placeLat");
		String placeLng = map.get("placeLng");
		String placeId = map.get("placeId");
		String placeContent = map.get("placeContent");
		//String memEmail = map.get("memEmail");
		int boardNum = Integer.parseInt(map.get("boardNum"));
		this.placeService.save(placeTitle, placeLat, placeLng, placeId, placeContent, null, boardNum);
		
		try {
			
			//DB처리 후 서비스에서 Place PK를 반환 받아옴
			tempMap.put("placeTitle", map.get("placeTitle")); //DB에 넣으려는 제목
			//DB에 저장되고 난 후 placeNo(PK) int값 String.valeuOf 로 타입변환
			
			
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		//타이틀이랑 PK를 Map으로 view로 내려보냄
		return new ResponseEntity<Map<String, String>>(tempMap, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("/deletePlace")
	public String deleteLocation(@RequestParam int placeNum) {
		placeRepository.deleteById((long) placeNum);
		return "success";
	}
}
