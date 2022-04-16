package com.example.topic_mq.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 17:28 2022/4/16
 * @ Description：
 */
@Configuration
public class DeadQueue_config {

	public static final String D_QUEUE_NAME = "DEAD_QUEUE";
	public static final String D_EXCHANGE = "DEAD_EXCHANGE";

	public static final String NORMAL_QUEUE = "NORMAL_QUEUE";
	public static final String NORMAL_EXCHANGE = "NORMAL_EXCHANGE";

	public static final String ROUTING_KEY = "ROUTING_KEY";


	//正常队列
	@Bean
	Queue normalqueue() {
		Map<String, Object> map = new HashMap<>(2);
		//当前队列绑定的死信交换机
		map.put("x-dead-letter-exchange", DeadQueue_config.D_EXCHANGE);

		//死信交换机的路由key
		map.put("x-dead-letter-routing-key", DeadQueue_config.ROUTING_KEY);
		return QueueBuilder.durable(DeadQueue_config.NORMAL_QUEUE).withArguments(map).build();
//		return new Queue(DeadQueue_config.NORMAL_QUEUE);
	}
	//死信队列
	@Bean
	Queue deadqueue() {
		return new Queue(DeadQueue_config.D_QUEUE_NAME);
	}

	//正常交换机
	@Bean
	FanoutExchange normal_exchange() {
		return new FanoutExchange(DeadQueue_config.NORMAL_EXCHANGE);
	}

	//死信交换机
	@Bean
	DirectExchange deadexchange() {
		return new DirectExchange(DeadQueue_config.D_EXCHANGE);
	}

	//死信绑定
	@Bean
	Binding dead_bind() {
		return BindingBuilder.bind(deadqueue()).to(deadexchange()).with(ROUTING_KEY);
	}

	//正常绑定
	@Bean
	Binding normai_bind() {
		return BindingBuilder.bind(normalqueue()).to(normal_exchange());
	}
}
