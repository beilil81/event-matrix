package com.humanup.eventmatrix.bs.impl;

import com.humanup.eventmatrix.aop.dto.EventException;
import com.humanup.eventmatrix.bs.EventBS;
import com.humanup.eventmatrix.bs.impl.sender.RabbitMQEventSender;
import com.humanup.eventmatrix.dao.EventDAO;
import com.humanup.eventmatrix.dao.TypeEventsDAO;
import com.humanup.eventmatrix.dao.entities.Event;
import com.humanup.eventmatrix.dao.entities.TypeEvents;
import com.humanup.eventmatrix.vo.EventVO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EventBSImpl implements EventBS {
  private static final Logger LOGGER = LoggerFactory.getLogger(EventBSImpl.class);

  @Autowired private EventDAO eventDAO;

  @Autowired private TypeEventsDAO typeEventsDAO;

  @Autowired RabbitMQEventSender rabbitMQEventSender;

  @Override
  @Transactional(transactionManager = "transactionManagerWrite", rollbackFor = EventException.class)
  public boolean createEvent(EventVO eventVO) throws EventException {
    if (null == eventVO) throw new EventException();
    rabbitMQEventSender.send(eventVO);
    return true;
  }

  @Override
  public EventVO findEventByLibelle(String libelle) {
    Optional<Event> eventFinded = Optional.ofNullable(eventDAO.findEventByLibelle(libelle));
    if (eventFinded.isPresent()) {
      TypeEvents typeEvents = eventFinded.get().getTypeEvents();
      return EventVO.builder()
          .libelle(eventFinded.get().getLibelle())
          .description(eventFinded.get().getDescription())
          .typeId(typeEvents.getTypeId())
          .idTypeEvents(typeEvents.getTypeId())
          .emailPerson(eventFinded.get().getEmailPerson())
          .build();
    }
    return null;
  }

  @Override
  public List<EventVO> findEventByEmailPerson(String emailPerson) {

    return eventDAO.findEventByEmailPerson(emailPerson).stream()
        .map(
            eventFinded ->
                EventVO.builder()
                    .libelle(eventFinded.getLibelle())
                    .description(eventFinded.getDescription())
                    .typeId(eventFinded.getTypeEvents().getTypeId())
                    .emailPerson(eventFinded.getEmailPerson())
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<EventVO> findListEventByTypeTitle(String titleEvent) {
    return eventDAO.findListEventByTypeTitle(titleEvent).stream()
        .map(
            eventFinded ->
                EventVO.builder()
                    .libelle(eventFinded.getLibelle())
                    .description(eventFinded.getDescription())
                    .typeId(eventFinded.getTypeEvents().getTypeId())
                    .emailPerson(eventFinded.getEmailPerson())
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<EventVO> findListEvent() {
    return eventDAO.findAll().stream()
        .map(
            EventFinded ->
                EventVO.builder()
                    .libelle(EventFinded.getLibelle())
                    .description(EventFinded.getDescription())
                    .typeId(EventFinded.getTypeEvents().getTypeId())
                    .emailPerson(EventFinded.getEmailPerson())
                    .build())
        .collect(Collectors.toList());
  }
}
