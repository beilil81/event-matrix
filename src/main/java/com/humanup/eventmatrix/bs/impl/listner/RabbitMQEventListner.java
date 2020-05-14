package com.humanup.eventmatrix.bs.impl.listner;

import com.humanup.eventmatrix.dao.EventDAO;
import com.humanup.eventmatrix.dao.TypeEventsDAO;
import com.humanup.eventmatrix.dao.entities.Event;
import com.humanup.eventmatrix.dao.entities.TypeEvents;
import com.humanup.eventmatrix.vo.EventVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQEventListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQEventListner.class);

  @Autowired private EventDAO eventDAO;
  @Autowired private TypeEventsDAO typeEventsDAO;

  @RabbitListener(queues = {"${event.queue.name}"})
  public void receive(EventVO eventVO) {
    try {
      LOGGER.info("Receive  message... {} ", eventVO.toString());
      TypeEvents typeEvents = typeEventsDAO.findByTypeId(eventVO.getTypeId());
      Event eventToSave =
          Event.builder()
              .libelle(eventVO.getLibelle())
              .description(eventVO.getDescription())
              .typeEvents(typeEvents)
              .emailPerson(eventVO.getEmailPerson())
              .build();
      eventDAO.save(eventToSave);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }
}
