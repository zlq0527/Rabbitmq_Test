package com.example.topic_mq.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 20:24 2022/4/12
 * @ Description：
 */
@Configuration
public class Topic_config {
	public static final String XIAOMI_QUEUE = "XIAOMI";
	public static final String HUAWEI_QUEUE = "HUAWEI";
	public static final String PHONE_QUEUE = "PHONE";

	public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";

	@Bean
	Queue XIAOMI() {
		return new Queue(Topic_config.XIAOMI_QUEUE);
	}
	@Bean
	Queue HUAWEI() {
		return new Queue(Topic_config.HUAWEI_QUEUE);
	}
	@Bean
	Queue PHONE() {
		return new Queue(Topic_config.PHONE_QUEUE);
	}
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(Topic_config.TOPIC_EXCHANGE);
	}

	@Bean
	Binding xiaomibind() {
		return BindingBuilder.bind(XIAOMI()).to(topicExchange()).with("xiaomi.#");
	}
	@Bean
	Binding huaweibind() {
		return BindingBuilder.bind(HUAWEI()).to(topicExchange()).with("huawei.#");
	}
	@Bean
	Binding phonebind() {
		return BindingBuilder.bind(PHONE()).to(topicExchange()).with("#.phone.#");
	}



}
