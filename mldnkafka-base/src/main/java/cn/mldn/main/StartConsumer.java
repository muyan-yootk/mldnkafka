package cn.mldn.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartConsumer {
	static {
		System.setProperty("java.security.auth.login.config",
				"d:/kafka/kafka_client_jaas.conf"); 
	} 
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml") ;
	}
}
