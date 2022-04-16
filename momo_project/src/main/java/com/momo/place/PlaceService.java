package com.momo.place;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
			
	public void delete(int placeNum) {
		placeRepository.deleteById((long) placeNum);
	}
}
