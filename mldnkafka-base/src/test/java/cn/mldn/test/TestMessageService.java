package cn.mldn.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.service.IMessageService;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageService {
	@Autowired
	private IMessageService messageService ;
	static {
		System.setProperty("java.security.auth.login.config",
				"d:/kafka/kafka_client_jaas.conf"); 
	}
	@Test
	public void testSend() throws Exception {
		for (int x = 0 ; x < 1000 ; x ++) {
			TimeUnit.SECONDS.sleep(1);
			this.messageService.sendMessge(x, "mldnjava - " + x);
		}
	}
}
