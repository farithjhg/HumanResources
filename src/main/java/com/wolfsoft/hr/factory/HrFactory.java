package com.wolfsoft.hr.factory;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.wolfsoft.hr.service.UserService;

public class HrFactory {
	private static GenericXmlApplicationContext ctx;
	private static UserService userService;
	
	public static UserService getUserService(){
		if(ctx==null)
			ctx=getCtx();
		
		if(userService==null)
			userService = (UserService) ctx
				.getBean("userService");
		
		return userService;
	}	
	
	public static GenericXmlApplicationContext getCtx() {
		ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		System.out.println("App Context initialized successfully");
		
		return ctx;
	}	
}
