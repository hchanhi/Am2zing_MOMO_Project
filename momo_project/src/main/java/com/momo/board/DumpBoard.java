/*
 * package com.momo.board;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.CommandLineRunner; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * 
 * import com.momo.domain.Board; import com.momo.domain.Member; import
 * com.momo.domain.Place; import com.momo.member.DumpMember; import
 * com.momo.place.PlaceRepository;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * @Configuration public class DumpBoard implements CommandLineRunner{
 * 
 * @Autowired private BoardRepository boardRepository;
 * 
 * @Autowired private PlaceRepository placeRepository;
 * 
 * @Autowired DumpMember dumpMember;
 * 
 * 
 * @Override public void run(String... args) throws Exception {
 * 
 * 
 * 
 * 
 * Place place1 = placeRepository.save(Place.builder() .placeTitle("여기가 존맛 맛집!")
 * .placeContent("분위기도 좋고 깔끔하고 일단 맛이 최고!") .placeId("스타벅스 강남점")
 * .placeLat("37.506229157876774") .placeLng("127.05688880326076")
 * .board.setBoardNum(1) .member.setMemId((long) 2) .build());
 * 
 * Board board1 = boardRepository.save(Board.builder()
 * .boardTitle("오늘은 여기서 먹을까?") .member.getMemEmail("aaaa@aaaa.com")
 * 
 * }}
 */