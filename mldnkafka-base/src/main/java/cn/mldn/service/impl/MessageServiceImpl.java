package cn.mldn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.service.IMessageService;
@Service
public class MessageServiceImpl implements IMessageService {
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate ;
	@Override
	public void sendMessge(Integer key, String value) {
		this.kafkaTemplate.sendDefault(key, value) ; // 消息发送
	}

}
