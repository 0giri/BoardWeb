package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardDTO;

@Repository
public class BoardDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String BOARD_INSERT = "insert into board(title, writer, content) values(?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=?, where seq=?";
	private final String BOARD_DELETE = "delect board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";

	// 글 등록
	public void insertBoard(BoardDTO dto) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, dto.getTitle(), dto.getWriter(), dto.getContent());
	}

	// 글 수정
	public void updateBoard(BoardDTO dto) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, dto.getTitle(), dto.getContent(), dto.getSeq());
	}

	// 글 삭제
	public void deleteBoard(BoardDTO dto) {
		System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, dto.getSeq());
	}

	// 글 상세 조회
	public BoardDTO getBoard(BoardDTO dto) {
		System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
		Object[] args = { dto.getSeq() };
		return jdbcTemplate.queryForObject(BOARD_GET, new BoardRowMapper(), args);
	}

	// 글 목록 조회
	public List<BoardDTO> getBoardList() {
		System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
}

class BoardRowMapper implements RowMapper<BoardDTO> {
	@Override
	public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setSeq(rs.getInt("seq"));
		board.setTitle(rs.getString("title"));
		board.setWriter(rs.getString("writer"));
		board.setContent(rs.getString("content"));
		board.setRegdate(rs.getDate("regdate"));
		board.setCnt(rs.getInt("cnt"));
		return board;
	}
}
