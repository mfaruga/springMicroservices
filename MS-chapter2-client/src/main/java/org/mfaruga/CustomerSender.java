package org.mfaruga;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class CustomerSender {

	@Autowired
	RabbitMessagingTemplate template;

	@Bean
	Queue queue() {
		return new Queue("CustomerQ", false);
	}

	public void send(String message) {
		template.convertAndSend("CustomerQ", message);
	}

}
