package com.humanup.eventmatrix.bs.impl.sender;

import com.humanup.eventmatrix.vo.TypeEventsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQTypeEventsSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQTypeEventsSender.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQTypeEventsSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${typeevent.queue.name}")
  String queueName;

  public void send(TypeEventsVO typeEvents) {
    LOGGER.info("Sending message... {} ", typeEvents.toString());
    rabbitTemplate.convertAndSend(queueName, typeEvents);
  }
}
