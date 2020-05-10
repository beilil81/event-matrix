package com.humanup.eventmatrix.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(of = {"idTypeEvents", "libelle", "description", "typeId","emailPerson"})
public class EventVO implements Serializable {
  @JsonIgnore Long idTypeEvents;
  String libelle;
  String description;
  Long typeId;
  String emailPerson;
}
