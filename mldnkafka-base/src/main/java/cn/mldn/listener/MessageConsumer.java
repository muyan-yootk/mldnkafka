package cn.mldn.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
public class MessageConsumer implements MessageListener<Integer, String> {
	@Override
	public void onMessage(ConsumerRecord<Integer, String> data) {
		// Kafka传统的消费端每一次需要批量的接收ConumserRecoder，但是Spring里面帮你处理这部分操作，你只需要处理一个消息记录即可
		System.out.println("【Kafka-Consumer】key = " + data.key() + "、value = " + data.value());
	}
}
