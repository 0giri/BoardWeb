package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {

	public static void main(String[] args) {
		// 스프링 컨테이너 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		// 컨테이너로부터 서비스 구현 객체를 Lookup한다.
		BoardService boardService = (BoardService) container.getBean("boardService");

		// 글 등록 기능 테스트
		BoardDTO dto = new BoardDTO();
		dto.setTitle("하이요");
		dto.setWriter("기리");
		dto.setContent("헬로우~");
		boardService.insertBoard(dto);

		// 글 목록 조회 기능 테스트
		List<BoardDTO> boardList = boardService.getBoardList(dto);
		for (BoardDTO board : boardList) {
			System.out.println(board.toString());
		}

		// 컨테이너 종료
		container.close();
	}

}
