package com.example.topic_mq.Consume;
import com.example.topic_mq.Config.Fanout_config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 19:57 2022/4/12
 * @ Description：
 */
@Component
public class Fanout_consume {
	@RabbitListener(queues = Fanout_config.QUEUENAME1)
	public void get1(String msg) {
		System.out.println("funout queue1 receive-->"+msg);
	}
	@RabbitListener(queues = Fanout_config.QUEUENAME2)
	public void get2(String msg) {
		System.out.println("funout queue2 receive-->"+msg);
	}
}
