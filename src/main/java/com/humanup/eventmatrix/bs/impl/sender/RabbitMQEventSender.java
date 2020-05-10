package com.humanup.eventmatrix.bs.impl.sender;

import com.humanup.eventmatrix.vo.EventVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQEventSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQEventSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQEventSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${event.queue.name}")
    String queueName;

    public void send(EventVO event) {
        LOGGER.info("Sending message... {} ", event.toString());
        rabbitTemplate.convertAndSend(queueName, event);
    }
}
