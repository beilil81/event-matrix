package com.humanup.eventmatrix.controllers;

import com.humanup.eventmatrix.aop.dto.EventException;
import com.humanup.eventmatrix.bs.EventBS;
import com.humanup.eventmatrix.vo.EventVO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
  @Autowired private EventBS eventBS;

  @Operation(
      summary = "Create Event",
      description = " Create new Event by Libelle, Description ...",
      tags = {"event"})
  @RequestMapping(
      value = "/event",
      method = RequestMethod.POST,
      consumes = {"application/json"})
  @ResponseBody
  public ResponseEntity createEvent(@RequestBody EventVO event) throws EventException {
    Optional<Object> findevent =
        Optional.ofNullable(eventBS.findEventByLibelle(event.getLibelle()));

    if (findevent.isPresent() && null != findevent.get()) {
      return ResponseEntity.status(HttpStatus.FOUND).body("This Event is Founded");
    }
    eventBS.createEvent(event);
    return ResponseEntity.status(HttpStatus.CREATED).body(event);
  }

  @Operation(
      summary = "Find event by libelle",
      description = "Event search by %libelle% format",
      tags = {"event"})
  @RequestMapping(value = "/event", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getEventInfo(
      @RequestParam(value = "libelle", defaultValue = "C++") String libelle) {
    Optional<EventVO> findEvent = Optional.ofNullable(eventBS.findEventByLibelle(libelle));
    if (findEvent.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(findEvent.get());
  }

  @Operation(
      summary = "Find all event",
      description = "Find all events",
      tags = {"event"})
  @RequestMapping(value = "/event/all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllEventInfo() {
    List<EventVO> findEvents = eventBS.findListEvent();

    if (findEvents.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(findEvents);
  }

  @Operation(
      summary = "Find all event by type",
      description = "Find all event by type title",
      tags = {"event"})
  @RequestMapping(value = "/event/all/type", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getTypeEvents(
      @RequestParam(value = "typeEvents", defaultValue = "audace") String typeEvents) {
    Optional<List<EventVO>> findEvent =
        Optional.ofNullable(eventBS.findListEventByTypeTitle(typeEvents));
    if (findEvent.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Type not Found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(findEvent.get());
  }

  @Operation(
      summary = "Find all events by person",
      description = "Find all event by person emailAdress",
      tags = {"event"})
  @RequestMapping(value = "/event/all/person", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getEventPerson(
      @RequestParam(value = "emailAddress", defaultValue = "mohamedbeilil83@gmail.com")
          String emailAddress) {
    Optional<List<EventVO>> findEvent =
        Optional.ofNullable(eventBS.findEventByEmailPerson(emailAddress));
    if (findEvent.get().isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This emailAddress not Found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(findEvent.get());
  }
}
