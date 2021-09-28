package org.springframework.samples.petclinic.web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.configuration.AMPQConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AMQPController {

	@Autowired
	RabbitTemplate rbtemplate;
	
	@GetMapping("/sendMessage")
	public String sendMessage(@RequestParam(name = "message",required = false) String message,@RequestParam(name = "topic",required = false) String topic) {
		if(message!=null && !"".equals(message)) {
			//rbtemplate.convertAndSend(AMPQConfiguration.topicExchangeName,topic!=null?topic:"",message);
			rbtemplate.convertAndSend(AMPQConfiguration.topicExchangeName,message);
		}
		return "welcome";
	}
}
