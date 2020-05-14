package com.humanup.eventmatrix.bs;

import com.humanup.eventmatrix.aop.dto.TypeEventException;
import com.humanup.eventmatrix.vo.TypeEventsVO;
import java.util.List;

public interface TypeEventsBS {
  boolean createTypeEvents(TypeEventsVO typeEventsVO) throws TypeEventException;

  TypeEventsVO findByTypeEventsTitle(String titleEvent);

  TypeEventsVO findByTypeEventsByID(Long id);

  List<TypeEventsVO> findListTypeEvents();

  List<TypeEventsVO> findListTypeEventsByTitle(String title);
}
