package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardDTO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {

	// 검색 카테고리 Map
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	// 글 등록
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardDTO dto, BoardDAO boardDAO) {
		boardDAO.insertBoard(dto);
		return "getBoardList.do";
	}

	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardDTO dto, BoardDAO boardDAO) {
		boardDAO.updateBoard(dto);
		return "getBoardList.do";
	}

	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardDTO dto, BoardDAO boardDAO) {
		boardDAO.deleteBoard(dto);
		return "getBoardList.do";
	}

	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardDTO dto, BoardDAO boardDAO, Model model) {
		model.addAttribute("board", boardDAO.getBoard(dto));
		return "getBoard.jsp";
	}

	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardDTO dto, BoardDAO boardDAO, Model model) {
		model.addAttribute("boardList", boardDAO.getBoardList(dto));
		return "getBoardList.jsp";
	}
}
