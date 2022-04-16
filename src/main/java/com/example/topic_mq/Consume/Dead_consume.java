package com.example.topic_mq.Consume;

import com.example.topic_mq.Config.DeadQueue_config;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 17:43 2022/4/16
 * @ Description：
 */
@Component
public class Dead_consume {

	@RabbitListener(queues = DeadQueue_config.NORMAL_QUEUE)
	public void receive(Message msg1, Channel channel) throws IOException {
		String msg = new String(msg1.getBody());
		System.out.println("接受到业务消息" + msg);
		System.out.println("-----------");
		boolean ack = true;
		try {
			if (msg.contains("error")) {
				throw new RuntimeException("dead letter exception");
			}
		} catch (Exception e) {
			ack = false;
		}
		if (!ack) {
			System.out.println("消息接受异常");
			channel.basicNack(msg1.getMessageProperties().getDeliveryTag(),false,false);
		} else {
			channel.basicAck(msg1.getMessageProperties().getDeliveryTag(),false);
		}

	}

	@RabbitListener(queues = DeadQueue_config.D_QUEUE_NAME)
	public void receive2(Message message,Channel channel) throws IOException {
		String msg = new String(message.getBody());
		System.out.println("死信队列接受到的消息>>>"+msg);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}
}
