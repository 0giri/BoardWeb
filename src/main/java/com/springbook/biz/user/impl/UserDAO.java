package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserDTO;

@Repository("userDAO")
public class UserDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private final String USER_GET = "select * from users where id=? and pwd=?";

	// 회원 정보 구하기
	public UserDTO getUser(UserDTO dto) {
		System.out.println("getUser()");
		UserDTO user = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new UserDTO();
				user.setId(rs.getString("id"));
				user.setPwd(rs.getString("pwd"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.rsClose(rs);
			JDBCUtil.stmtClose(pstmt);
			JDBCUtil.connClose(conn);
		}
		return user;
	}
}
