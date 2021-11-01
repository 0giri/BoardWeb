package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.user.UserDTO;
import com.springbook.biz.user.impl.UserDAO;
import com.springbook.view.controller.Controller;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setPwd(password);

		UserDAO userDAO = new UserDAO();
		UserDTO user = userDAO.getUser(dto);

		if (user != null) {
			return "getBoardList.do";
		} else {
			return "login";
		}
	}
}
