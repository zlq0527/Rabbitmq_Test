package com.example.topic_mq.Consume;

import com.example.topic_mq.Config.Direct_config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:13 2022/4/12
 * @ Description：
 */
@Component
public class Direct_consume {

	@RabbitListener(queues = Direct_config.QUEUE_NAME)
	public void get(String mes) {
		System.out.println("queue1 receive >>>"+mes);
	}
	@RabbitListener(queues = Direct_config.QUEUE_NAME2)
	public void get2(String mes) {
		System.out.println("queue2 receive >>>"+mes);
	}
}
