package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardDTO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.common.LogAdvice;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAOSpring boardDAO;

	@Override
	public void insertBoard(BoardDTO dto) {
		boardDAO.insertBoard(dto);
	}

	@Override
	public void updateBoard(BoardDTO dto) {
		boardDAO.updateBoard(dto);
	}

	@Override
	public void deleteBoard(BoardDTO dto) {
		boardDAO.deleteBoard(dto);
	}

	@Override
	public BoardDTO getBoard(BoardDTO dto) {
		return boardDAO.getBoard(dto);
	}

	@Override
	public List<BoardDTO> getBoardList() {
		return boardDAO.getBoardList();
	}

}
