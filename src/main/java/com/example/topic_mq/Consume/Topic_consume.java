package com.example.topic_mq.Consume;

import com.example.topic_mq.Config.Topic_config;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 20:30 2022/4/12
 * @ Description：
 */
@Component
public class Topic_consume {
	@RabbitListener(queues = Topic_config.HUAWEI_QUEUE)
	public void huawei(String msg) {
		System.out.println("huawei receive  "+msg);
	}
	@RabbitListener(queues = Topic_config.XIAOMI_QUEUE)
	public void xiaomi(String msg) {
		System.out.println("xiaomi receive  "+msg);
	}
	@RabbitListener(queues = Topic_config.PHONE_QUEUE)
	public void phone(String msg) {
		System.out.println("phone receive  "+msg);
	}
}
