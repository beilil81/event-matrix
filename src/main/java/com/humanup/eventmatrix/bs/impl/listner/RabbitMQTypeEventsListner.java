package com.humanup.eventmatrix.bs.impl.listner;

import com.humanup.eventmatrix.dao.TypeEventsDAO;
import com.humanup.eventmatrix.dao.entities.TypeEvents;
import com.humanup.eventmatrix.vo.TypeEventsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQTypeEventsListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQTypeEventsListner.class);
  @Autowired private TypeEventsDAO typeEventsDAO;

  @RabbitListener(queues = {"${typeevent.queue.name}"})
  public void receive(TypeEventsVO typeEventsVO) {
    try {
      LOGGER.info("Receive  message... {} ", typeEventsVO.toString());
      TypeEvents typeToSave = TypeEvents.builder().titleEvent(typeEventsVO.getTitleEvent()).build();
      typeEventsDAO.save(typeToSave);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }
}
