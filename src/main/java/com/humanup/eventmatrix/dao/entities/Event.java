package com.humanup.eventmatrix.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"id", "libelle", "description", "typeEvents", "emailPerson"})
@Entity
@Table(name = "event")
public class Event implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "libelle")
  String libelle;

  @Column(name = "description")
  String description;

  @ManyToOne
  @JoinColumn(name = "type_id")
  TypeEvents typeEvents;

  @Column(name = "email_person")
  String emailPerson;
}
