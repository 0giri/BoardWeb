package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserDTO;
import com.springbook.biz.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDTO getUser(UserDTO dto) {
		return userDAO.getUser(dto);
	}

}
