package com.humanup.eventmatrix.controllers;

import com.humanup.eventmatrix.aop.dto.TypeEventException;
import com.humanup.eventmatrix.bs.TypeEventsBS;
import com.humanup.eventmatrix.vo.TypeEventsVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TypeEventsController {
    @Autowired
    private TypeEventsBS typeEventsBS;

    @Operation(
            summary = "Create Type Events",
            description = " Create new type events by title ...",
            tags = {"typeevents"})
    @RequestMapping(
            value = "/typeevents",
            method = RequestMethod.POST,
            consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity createTypeEvents(@RequestBody TypeEventsVO typeEvents) throws TypeEventException {
        Optional<Object> findTypeEvents =
                Optional.ofNullable(typeEventsBS.findByTypeEventsTitle(typeEvents.getTitleEvent()));

        if (findTypeEvents.isPresent() && null != findTypeEvents.get()) {
            return ResponseEntity.status(HttpStatus.FOUND).body("This type is Founded");
        }
        typeEventsBS.createTypeEvents(typeEvents);
        return ResponseEntity.status(HttpStatus.CREATED).body(typeEventsBS);
    }

    @Operation(
            summary = "Find types events by title",
            description = "Type Events search by %title% format",
            tags = {"typeevents"})
    @RequestMapping(value = "/typeevents/title", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getTypeInfo(
            @RequestParam(value = "title", defaultValue = "Spring Boot") String title) {
        Optional<TypeEventsVO> findTypeEventsTitle =
                Optional.ofNullable(typeEventsBS.findByTypeEventsTitle(title));
        if (findTypeEventsTitle.isEmpty()) {
            Optional<List<TypeEventsVO>> findType =
                    Optional.ofNullable(typeEventsBS.findListTypeEventsByTitle(title));
            if (findType.get().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This type is not Found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(findType.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(findTypeEventsTitle.get());
    }

    @Operation(
            summary = "Find types events by id",
            description = "Type Events search by %id% format",
            tags = {"typeevents"})
    @RequestMapping(value = "/typesevents/id", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getTypeInfoById(@RequestParam(value = "id", defaultValue = "1") Long id) {
        Optional<TypeEventsVO> findType = Optional.ofNullable(typeEventsBS.findByTypeEventsByID(id));
        if (findType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This type is not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(findType.get());
    }

    @Operation(
            summary = "Find all type events",
            description = "Find all  type event",
            tags = {"typeevents"})
    @RequestMapping(value = "/typeevents/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAlltypeInfo() {
        List<TypeEventsVO> findType = typeEventsBS.findListTypeEvents();

        if (findType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findType);
    }
}
