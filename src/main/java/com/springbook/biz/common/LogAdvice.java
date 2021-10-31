package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogAdvice {
	@Before("PointcutCommon.allPointcut()")
	public void printLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		System.out.println("[사전 처리] " + method + "() 메소드 실행");
	}
}
