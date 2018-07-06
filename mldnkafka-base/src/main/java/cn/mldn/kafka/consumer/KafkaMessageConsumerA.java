package cn.mldn.kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaMessageConsumerA {
	public static final String TOPIC_NAME = "mldn-topic" ;	// 主题
	public static final String SERVERS = "kafka-single:9095" ;	// 主机列表
	public static final String GROUP = "group-1" ;	// 主机列表
	public static final int POLL_TIMEOUT = 1000 ;  
	static {
		System.setProperty("java.security.auth.login.config",
				"d:/kafka/kafka_client_jaas.conf");
	}
	public static void main(String[] args) throws Exception {
		// 现在使用的是新一代的Kafka客户端，此时连接的一定是Kafka服务器而不是ZooKeeper主机
		Properties props = new Properties() ;	// 定义Kafka的连接属性
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS) ; // 主机
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName()); // Key类型为Integer
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // Value类型为String
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP) ;
		props.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN");
		props.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
		Consumer<Integer,String> consumer = new KafkaConsumer<>(props) ;
		consumer.subscribe(Arrays.asList(TOPIC_NAME));// 定义监听主题，每个消费者可以监听多个主题
		boolean flag = true ;
		while (flag) {
			// TimeUnit.SECONDS.sleep(1);
			// Kafka采用的是批量消费处理， 所以此时返回的是一组消费内容
			ConsumerRecords<Integer, String> records = consumer.poll(POLL_TIMEOUT) ;
			for (ConsumerRecord<Integer, String> record : records) {
				System.out.println("【消费端 - A】offset = " + record.offset() + "、key = " + record.key() + "、value = " + record.value());
			}
		}
		consumer.close(); 
	}
}
