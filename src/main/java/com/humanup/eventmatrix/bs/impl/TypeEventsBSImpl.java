package com.humanup.eventmatrix.bs.impl;

import com.humanup.eventmatrix.aop.dto.EventException;
import com.humanup.eventmatrix.aop.dto.TypeEventException;
import com.humanup.eventmatrix.bs.TypeEventsBS;
import com.humanup.eventmatrix.bs.impl.sender.RabbitMQTypeEventsSender;
import com.humanup.eventmatrix.dao.TypeEventsDAO;
import com.humanup.eventmatrix.dao.entities.TypeEvents;
import com.humanup.eventmatrix.vo.TypeEventsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TypeEventsBSImpl implements TypeEventsBS {

  @Autowired private TypeEventsDAO typeEventsDAO;

  @Autowired
  RabbitMQTypeEventsSender rabbitMQTypeEventSender;

  @Override
  @Transactional(transactionManager = "transactionManagerWrite",rollbackFor = TypeEventException.class)
  public boolean createTypeEvents(TypeEventsVO typeEventsVO) throws TypeEventException {
    if (null == typeEventsVO) throw new TypeEventException();
    rabbitMQTypeEventSender.send(typeEventsVO);
    return true;
  }

  @Override
  public TypeEventsVO findByTypeEventsTitle(String titleEvent) {
    Optional<TypeEvents> typeEventsFinded =
        Optional.ofNullable(typeEventsDAO.findByTitleEvent(titleEvent));
    if (typeEventsFinded.isPresent()) {
      return TypeEventsVO.builder().titleEvent(typeEventsFinded.get().getTitleEvent()).build();
    }
    return null;
  }

  @Override
  public TypeEventsVO findByTypeEventsByID(Long id) {
    Optional<TypeEvents> typeEventsFinded = Optional.ofNullable(typeEventsDAO.findByTypeId(id));
    if (typeEventsFinded.isPresent()) {
      return TypeEventsVO.builder().titleEvent(typeEventsFinded.get().getTitleEvent()).build();
    }
    return null;
  }

  @Override
  public List<TypeEventsVO> findListTypeEvents() {
    return typeEventsDAO.findAll().stream()
        .map(
            typeEventsFinded ->
                TypeEventsVO.builder().titleEvent(typeEventsFinded.getTitleEvent()).build())
        .collect(Collectors.toList());
  }

  @Override
  public List<TypeEventsVO> findListTypeEventsByTitle(String title) {
    Optional<List<TypeEvents>> listTypeEventsFinded =
        Optional.ofNullable(typeEventsDAO.findTypeEventsByTitle(title));
    if (listTypeEventsFinded.isPresent()) {
      return listTypeEventsFinded.get().stream()
          .map(
              typeEventsFinded ->
                  TypeEventsVO.builder().titleEvent(typeEventsFinded.getTitleEvent()).build())
          .collect(Collectors.toList());
    }
    return null;
  }
}
