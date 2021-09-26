package org.springframework.samples.petclinic.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AMQPClient {
	@RabbitListener(queues = "myFootBallQueue")
	public void listen(String in) {
	    System.out.println("Message read from myQueue : " + in);
	}
}
