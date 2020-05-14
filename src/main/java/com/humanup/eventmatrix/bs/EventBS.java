package com.humanup.eventmatrix.bs;

import com.humanup.eventmatrix.aop.dto.EventException;
import com.humanup.eventmatrix.vo.EventVO;
import java.util.List;

public interface EventBS {
  boolean createEvent(EventVO Event) throws EventException;

  EventVO findEventByLibelle(String libelle);

  List<EventVO> findEventByEmailPerson(String emailPerson);

  List<EventVO> findListEvent();

  List<EventVO> findListEventByTypeTitle(String titleEvent);
}
