package com.pj.cloud_note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect

public class loggerBean {
	@Before("within(com.pj.cloud_note.controller..*)")
	public void logController() {
		System.out.println("进入Controller组件处理");
	}
	@Before("within(com.pj.cloud_note.service..*)")
	public void logService() {
		System.out.println("AOP注入Service");
	}
}
