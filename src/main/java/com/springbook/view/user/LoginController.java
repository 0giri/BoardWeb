package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.user.UserDTO;
import com.springbook.biz.user.impl.UserDAO;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setPwd(password);

		UserDAO userDAO = new UserDAO();
		UserDTO user = userDAO.getUser(dto);

		ModelAndView mav = new ModelAndView();
		if (user != null) {
			mav.setViewName("redirect:getBoardList.do");
		} else {
			mav.setViewName("redirect:login");
		}
		return mav;
	}
}
