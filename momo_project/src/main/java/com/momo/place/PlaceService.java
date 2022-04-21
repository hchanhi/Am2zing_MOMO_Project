package com.momo.place;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.momo.board.BoardRepository;

import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.member.MemberCustomRepository;
import com.momo.member.MemberRepository;

@Service
public class PlaceService {
	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberCustomRepository memberCustomRepository;
	
	public List<Place> findAll() {
		return this.placeRepository.findAll();
	}
	public List<Place> findByBoardNum(int boardNum){

		Board board = boardRepository.findById((long) boardNum).get();
		return this.placeRepository.findByBoard(board);
	}
	
	public void save(String placeTitle, String placeLat, String placeLng,
			String placeId, String placeContent, String memEmail, int boardNum, String placeImg) {
		Board board = boardRepository.findById((long) boardNum).get();
		Member member = memberCustomRepository.findByMemEmail(memEmail);
		Place place = new Place();
		place.setPlaceTitle(placeTitle);
		place.setPlaceLat(placeLat);
		place.setPlaceLng(placeLng);
		place.setPlaceId(placeId);
		place.setPlaceContent(placeContent);
		place.setMember(member);
		place.setBoard(board);
		place.setPlaceImg(placeImg);

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
		String memEmail = map.get("memEmail");
		//DB처리
		String placeTitle = map.get("placeTitle");
		String placeLat = map.get("placeLat");
		String placeLng = map.get("placeLng");
		String placeId = map.get("placeId");
		String placeContent = map.get("placeContent");
		String placeImg = map.get("placeImg");
		int boardNum = Integer.parseInt(map.get("boardNum"));
		this.save(placeTitle, placeLat, placeLng, placeId, placeContent, memEmail, boardNum, placeImg);
		
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
	
	
	public String upload(MultipartFile multi, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String path = new File("").getAbsolutePath()+"\\src\\main\\resources\\static\\images\\";
		String url = null;
		try {
			String uploadpath = path;
			String originFileName = multi.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf(".")
					,originFileName.length());
			long size = multi.getSize();
			String saveFileName = genSaveFileName(extName);
			
			if(!multi.isEmpty()) {
				File file = new File(uploadpath, multi.getOriginalFilename()+"_"+saveFileName);
				String name = multi.getOriginalFilename()+"_"+saveFileName;
				multi.transferTo(file);
				model.addAttribute("fileName", multi.getOriginalFilename());
				//model.addAttribute("uploadPath", file.getAbsolutePath());
				model.addAttribute("uploadPath", "/images/"+name);
				//return file.getAbsolutePath();
				return "Board/filePath";
			}
		} catch(Exception e){
			
		}
		return "Board/filePath";
	}
	
	private String genSaveFileName(String extName) {
        String fileName = "";
        
        Calendar calendar = Calendar.getInstance();
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += calendar.get(Calendar.MILLISECOND);
        fileName += extName;
        return fileName;
    }
}
