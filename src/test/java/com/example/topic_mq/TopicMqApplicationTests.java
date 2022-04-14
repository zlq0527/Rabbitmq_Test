package com.example.topic_mq;
import com.example.topic_mq.Config.Direct_config;
import com.example.topic_mq.Config.Fanout_config;
import com.example.topic_mq.Config.Header_config;
import com.example.topic_mq.Config.Topic_config;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TopicMqApplicationTests {
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Test
	void test2() {
		for (int i = 0; i < 5; i++) {
			rabbitTemplate.convertAndSend(Direct_config.EXCHANGE, "queue2", "msg" + i);
		}
	}

	@Test
	void test() {
		rabbitTemplate.convertAndSend(Fanout_config.EXCHANGE, null, "hello,fanout");
	}

	@Test
	void Topictest() {
		rabbitTemplate.convertAndSend(Topic_config.TOPIC_EXCHANGE, "xiaomi.abc.phone.abc", "小米请接收,还有一个手机");
	}

	@Test
	void header_() {
		Message msg = MessageBuilder.withBody("hello header_config".getBytes()).setHeader("name","aaa").build();
		rabbitTemplate.convertAndSend(Header_config.EXCHANGE,null,msg);

		Message age = MessageBuilder.withBody("age__".getBytes()).setHeader("age", 10).build();
		rabbitTemplate.convertAndSend(Header_config.EXCHANGE, null, age);
	}
}
