package com.humanup.eventmatrix.dao.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"typeId", "titleEvent", "eventList"})
@Entity
@Table(name = "type_events")
public class TypeEvents implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "type_id")
  Long typeId;

  @Column(name = "title_event")
  String titleEvent;

  @OneToMany(mappedBy = "typeEvents", fetch = FetchType.LAZY)
  List<Event> eventList;
}
