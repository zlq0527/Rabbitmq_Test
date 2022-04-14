package com.example.topic_mq.Config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 19:56 2022/4/12
 * @ Description：
 */
@Configuration
public class Fanout_config {
	public static final String QUEUENAME1 = "QUEUE1";
	public static final String QUEUENAME2 = "QUEUE2";
	public static final String EXCHANGE = "EXCHANGE1";

	@Bean
	Queue newqueue() {
		return new Queue(Fanout_config.QUEUENAME1);
	}

	@Bean
	Queue newqueue2() {
		return new Queue(Fanout_config.QUEUENAME2);
	}

	@Bean
	FanoutExchange newexchange() {
		return new FanoutExchange(Fanout_config.EXCHANGE);
	}

	@Bean
	Binding Fanoutbind1() {
		return BindingBuilder.bind(newqueue()).to(newexchange());
	}

	@Bean
	Binding Fanoutbind2() {
		return BindingBuilder.bind(newqueue2()).to(newexchange());
	}


}
