package com.springbook.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

	public static void main(String[] args) {
		// 스프링 컨테이너 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		// 컨테이너로부터 서비스 구현 객체를 Lookup한다.
		UserService userService = (UserService) container.getBean("userService");

		// 로그인 기능 테스트
		UserDTO dto = new UserDTO();
		dto.setId("test");
		dto.setPwd("test123");
		UserDTO user = userService.getUser(dto);

		if (user != null) {
			System.out.println(user.getName() + "님 환영합니다.");
		} else {
			System.out.println("로그인 실패");
		}

		// 컨테이너 종료
		container.close();
	}

}
