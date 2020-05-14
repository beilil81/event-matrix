package com.humanup.eventmatrix.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(of = {"idTypeEvents", "libelle", "description", "typeId", "emailPerson"})
public class EventVO implements Serializable {
  @JsonIgnore Long idTypeEvents;
  String libelle;
  String description;
  Long typeId;
  String emailPerson;
}
