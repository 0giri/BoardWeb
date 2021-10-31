package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardDTO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserDTO;
import com.springbook.biz.user.impl.UserDAO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);

		HttpSession session = request.getSession();

		if (path.equals("/login.do")) {
			System.out.println("로그인 처리");

			String id = request.getParameter("id");
			String password = request.getParameter("password");

			UserDTO dto = new UserDTO();
			dto.setId(id);
			dto.setPwd(password);

			UserDAO userDAO = new UserDAO();
			UserDTO user = userDAO.getUser(dto);

			if (user != null) {
				response.sendRedirect("getBoardList.do");
			} else {
				response.sendRedirect("login.jsp");
			}

		} else if (path.equals("/logout.do")) {
			System.out.println("로그아웃 처리");

			session.invalidate();
			response.sendRedirect("login.jsp");

		} else if (path.equals("/insertBoard.do")) {
			System.out.println("글 등록 처리");

			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setWriter(writer);
			dto.setContent(content);

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(dto);

			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/updateBoard.do")) {
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

			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/deleteBoard.do")) {
			System.out.println("글 삭제 처리");

			String seq = request.getParameter("seq");

			BoardDTO dto = new BoardDTO();
			dto.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(dto);

			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/getBoard.do")) {
			System.out.println("글 상세 조회 처리");

			String seq = request.getParameter("seq");

			BoardDTO dto = new BoardDTO();
			dto.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			BoardDTO board = boardDAO.getBoard(dto);

			session.setAttribute("board", board);
			response.sendRedirect("getBoard.jsp");

		} else if (path.equals("/getBoardList.do")) {
			System.out.println("글 목록 검색 처리");

			BoardDTO dto = new BoardDTO();
			BoardDAO boardDAO = new BoardDAO();
			List<BoardDTO> boardList = boardDAO.getBoardList(dto);
			
			session.setAttribute("boardList", boardList);
			response.sendRedirect("getBoardList.jsp");
		}
	}
}
