package com.momo.place;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momo.board.BoardRepository;

import com.momo.domain.Board;

import com.momo.domain.Place;

@Service
public class PlaceService {
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	public List<Place> findAll() {
		return this.placeRepository.findAll();
	}
	public List<Place> findByBoardNum(int boardNum){
		Board board = boardRepository.findById((long) boardNum).get();
		return this.placeRepository.findByBoard(board);
	}
	
	public void save(String placeTitle, String placeLat, String placeLng,
			String placeId, String placeContent, String memEmail, int boardNum) {
		Board board = boardRepository.findById((long) boardNum).get();
		Place place = new Place();
		place.setPlaceTitle(placeTitle);
		place.setPlaceLat(placeLat);
		place.setPlaceLng(placeLng);
		place.setPlaceId(placeId);
		place.setPlaceContent(placeContent);
//		place.getMember().setMemEmail(memEmail);
		place.setBoard(board);
		//place.setPlaceImg(placeImg);
		this.placeRepository.save(place);
	}
			
	@Transactional
    public void deletePlace(Long boardNum) {
	 List<Place> places = this.findByBoardNum(boardNum.intValue());
	 
	 	for(Place place : places) {
			 Long placeNum = place.getPlaceNum();
			 placeRepository.deleteById(placeNum);
	 	}
    }
	
	public void delete(Long placeNum) {
		placeRepository.deleteById(placeNum);
	}
	
	public ResponseEntity<?> update(Map<String,String> map) {
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
		this.save(placeTitle, placeLat, placeLng, placeId, placeContent, null, boardNum);
		
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
	
	public List<Place> getPlaceList(int boardNum){
		Board board = boardRepository.findById((long) boardNum).get();
		List<Place> placeList = this.placeRepository.findByBoard(board);
		return placeList;
	}
}
