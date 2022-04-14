package com.example.topic_mq.Consume;

import com.example.topic_mq.Config.Header_config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 17:27 2022/4/14
 * @ Description：
 */
@Component
public class Header_consume {
	@RabbitListener(queues = Header_config.QUEUENAME1)
	public void namemsg(byte[] msg) {
		System.out.println("name>>>"+new String(msg, 0, msg.length));
	}

	@RabbitListener(queues = Header_config.QUEUENAME2)
	public void agemsg(byte[] msg) {
		System.out.println("age>>>" + new String(msg, 0, msg.length));
	}

}
