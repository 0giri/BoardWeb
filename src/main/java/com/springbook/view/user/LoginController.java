package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserDTO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserDTO dto) {
		System.out.println("로그인 화면으로 이동");
		dto.setId("test");
		dto.setPwd("test123");
		return "login.jsp";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserDTO dto, UserDAO userDAO, HttpSession session) {
		UserDTO user = userDAO.getUser(dto);
		if (user != null) {
			session.setAttribute("userName", user.getName());
			return "redirect:getBoardList.do";
		} else {
			return "login.jsp";
		}
	}
}
