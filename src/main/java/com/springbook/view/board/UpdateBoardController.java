package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.board.BoardDTO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.view.controller.Controller;

public class UpdateBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 수정 처리");

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String seq = request.getParameter("seq");

		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setSeq(Integer.parseInt(seq));

		BoardDAO boardDAO = new BoardDAO();
		boardDAO.updateBoard(dto);

		return "getBoardList.do";
	}
}