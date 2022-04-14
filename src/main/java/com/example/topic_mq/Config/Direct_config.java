package com.example.topic_mq.Config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:11 2022/4/12
 * @ Description：
 */
@Configuration
public class Direct_config {
	public static final String QUEUE_NAME = "T_QUEUE";
	public static final String QUEUE_NAME2 = "T_QUEUE2";
	public static final String EXCHANGE = "T_EXCHANGE";


	@Bean
	Queue DirectQueue1() {
		return new Queue(QUEUE_NAME);
	}

	@Bean
	Queue DirectQueue2() {
		return new Queue(QUEUE_NAME2);
	}

	@Bean
	DirectExchange newExchange() {
		return new DirectExchange(EXCHANGE);
	}

	@Bean
	Binding Directbind1() {
		return BindingBuilder.bind(DirectQueue1()).to(newExchange()).with("queue1");
	}

	@Bean
	Binding Directbind2() {
		return BindingBuilder.bind(DirectQueue2()).to(newExchange()).with("queue2");
	}
}
