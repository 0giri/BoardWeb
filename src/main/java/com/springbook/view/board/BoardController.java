package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardDTO;
import com.springbook.biz.board.BoardService;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	// 글 등록
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardDTO dto) throws IOException {
		MultipartFile uploadFile = dto.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("/Users/giri/Files/" + fileName));
		}
		boardService.insertBoard(dto);
		return "getBoardList.do";
	}

	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardDTO dto) {
		boardService.updateBoard(dto);
		return "getBoardList.do";
	}

	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardDTO dto) {
		boardService.deleteBoard(dto);
		return "getBoardList.do";
	}

	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardDTO dto, Model model) {
		model.addAttribute("board", boardService.getBoard(dto));
		return "getBoard.jsp";
	}

	// 검색 카테고리 Map
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardDTO dto, Model model) {
		if (dto.getSearchCondition() == null)
			dto.setSearchCondition("TITLE");
		if (dto.getSearchKeyword() == null)
			dto.setSearchKeyword("");
		System.out.println(dto.getSearchCondition());
		System.out.println(dto.getSearchKeyword());
		model.addAttribute("boardList", boardService.getBoardList(dto));
		return "getBoardList.jsp";
	}
}
