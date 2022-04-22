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
			//basicNack方法
			// 第二个参数 multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
			// 第三个参数 requeue 重新进入队列 false含义是是直接把消息丢弃
			channel.basicNack(msg1.getMessageProperties().getDeliveryTag(), false, false);
//			如果channel.basicNack(8, true, false);表示deliveryTag=8之前未确认的消息都处理失败且将这些消息直接丢弃。
//			如果channel.basicNack(8, false, true);表示deliveryTag=8的消息处理失败且将该消息重新放回队列。
//			如果channel.basicNack(8, false, false);表示deliveryTag=8的消息处理失败且将该消息直接丢弃。
		} else {
			//basicAck 第一个参数 消息id
			// 第二个参数 multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息。
			channel.basicAck(msg1.getMessageProperties().getDeliveryTag(), false);
//			如果此时channel.basicAck(8, true);则表示前面未确认的5,6,7投递也一起确认处理完毕。
//			如果此时channel.basicAck(8, false);则仅表示deliveryTag=8的消息已经成功处理。

		}

	}

	@RabbitListener(queues = DeadQueue_config.D_QUEUE_NAME)
	public void receive2(Message message, Channel channel) throws IOException {
		String msg = new String(message.getBody());
		System.out.println("死信队列接受到的消息>>>" + msg);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
