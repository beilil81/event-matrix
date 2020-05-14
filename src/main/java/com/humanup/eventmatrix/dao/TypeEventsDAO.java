package com.humanup.eventmatrix.dao;

import com.humanup.eventmatrix.dao.entities.TypeEvents;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeEventsDAO extends JpaRepository<TypeEvents, Long> {

  TypeEvents findByTitleEvent(String titleEvent);

  List<TypeEvents> findAll();

  TypeEvents findByTypeId(long typeId);

  @Query("SELECT t FROM TypeEvents t WHERE lower(t.titleEvent) like %:titleEvent% ")
  List<TypeEvents> findTypeEventsByTitle(String titleEvent);
}
