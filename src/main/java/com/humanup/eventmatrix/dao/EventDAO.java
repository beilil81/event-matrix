package com.humanup.eventmatrix.dao;

import com.humanup.eventmatrix.dao.entities.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDAO extends JpaRepository<Event, Long> {
  Event findEventByLibelle(String libelle);

  @Query("SELECT e FROM Event e WHERE lower(e.emailPerson) like %:emailPerson% ")
  List<Event> findEventByEmailPerson(String emailPerson);

  List<Event> findAll();

  Event findById(long id);

  @Query("SELECT s FROM Event s WHERE lower(s.typeEvents.titleEvent) like %:titleEvent% ")
  List<Event> findListEventByTypeTitle(String titleEvent);
}
