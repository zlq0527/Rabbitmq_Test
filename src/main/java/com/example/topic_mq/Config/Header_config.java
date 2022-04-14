package com.example.topic_mq.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 17:24 2022/4/14
 * @ Description：
 */
@Configuration
public class Header_config {
	public static final String QUEUENAME1 = "header_QUEUE1";
	public static final String QUEUENAME2 = "header_QUEUE2";
	public static final String EXCHANGE = "header_Exchange";

	@Bean
	Queue head_queue1() {
		return new Queue(Header_config.QUEUENAME1);
	}

	@Bean
	Queue head_queue2() {
		return new Queue(Header_config.QUEUENAME2);
	}

	@Bean
	HeadersExchange head_exchange() {
		return new HeadersExchange(Header_config.EXCHANGE);
	}

	@Bean
	Binding head_bind1() {
		return BindingBuilder.bind(head_queue1()).to(head_exchange()).where("name").exists();
	}

	@Bean
	Binding head_bind2() {
		return BindingBuilder.bind(head_queue2()).to(head_exchange()).where("age").matches(10);
	}
}
