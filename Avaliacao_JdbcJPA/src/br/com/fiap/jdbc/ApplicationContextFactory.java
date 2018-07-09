package br.com.fiap.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextFactory {
	private static ApplicationContext context;
	
	public static ApplicationContext getContext() {
		if (context == null) {
			 context = new ClassPathXmlApplicationContext("beanJdbc.xml");
		}
		return context;
	}
}
