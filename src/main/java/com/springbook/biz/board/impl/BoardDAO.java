package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardDTO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private final String BOARD_INSERT = "insert into board(title, writer, content) values(?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	private final String BOARD_LIST_T = "select * from board where title like '%'||?||'%' order by seq desc";
	private final String BOARD_LIST_C = "select * from board where content like '%'||?||'%' order by seq desc";

	// 글 등록
	public void insertBoard(BoardDTO dto) {
		System.out.println("insertBoard()");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.stmtClose(pstmt);
			JDBCUtil.connClose(conn);
		}
	}

	// 글 수정
	public void updateBoard(BoardDTO dto) {
		System.out.println("updateBoard()");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getSeq());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.stmtClose(pstmt);
			JDBCUtil.connClose(conn);
		}
	}

	// 글 삭제
	public void deleteBoard(BoardDTO dto) {
		System.out.println("deleteBoard()");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, dto.getSeq());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.stmtClose(pstmt);
			JDBCUtil.connClose(conn);
		}
	}

	// 글 상세 조회
	public BoardDTO getBoard(BoardDTO dto) {
		System.out.println("getBoard()");
		BoardDTO board = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, dto.getSeq());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new BoardDTO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.rsClose(rs);
			JDBCUtil.stmtClose(pstmt);
			JDBCUtil.connClose(conn);
		}
		return board;
	}

	// 글 목록 조회
	public List<BoardDTO> getBoardList(BoardDTO dto) {
		System.out.println("getBoardList()");
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		try {
			conn = JDBCUtil.getConnection();
			if (dto.getSearchCondition().equals("TITLE")) {
				pstmt = conn.prepareStatement(BOARD_LIST_T);
			} else if (dto.getSearchCondition().equals("CONTENT")) {
				pstmt = conn.prepareStatement(BOARD_LIST_C);
			}
			pstmt.setString(1, dto.getSearchKeyword());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.rsClose(rs);
			JDBCUtil.stmtClose(pstmt);
			JDBCUtil.connClose(conn);
		}
		return boardList;
	}
}
