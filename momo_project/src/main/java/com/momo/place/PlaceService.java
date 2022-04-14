package com.momo.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.board.BoardRepository;
import com.momo.board.MyRepository;
import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.domain.Place;

@Service
public class PlaceService {
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private BoardRepository boardRepository; 
	
	@Autowired
	private MyRepository myRepository;
	
	public List<Place> findAll() {
		return this.placeRepository.findAll();
	}
	public List<Place> findByBoardNum(int boardNum){
	      Board board = boardRepository.findById((long) boardNum).get();
	      return this.myRepository.findByBoard(board);
	   }
	
	public void save(String placeTitle, String placeLat, String placeLng,
			String placeId, String placeContent,  Board board) {
		Place place = new Place();
		place.setPlaceTitle(placeTitle);
		place.setPlaceLat(placeLat);
		place.setPlaceLng(placeLng);
		place.setPlaceId(placeId);
		place.setPlaceContent(placeContent);
		//place.getMember().setMemEmail(member.getMemEmail());
		place.getBoard().setBoardNum(board.getBoardNum());
		//place.setPlaceImg(placeImg);
		this.placeRepository.save(place);
	}
}
