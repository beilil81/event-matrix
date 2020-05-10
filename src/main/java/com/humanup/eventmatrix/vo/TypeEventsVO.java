package com.humanup.eventmatrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(of = {"titleEvent"})
public class TypeEventsVO implements Serializable {
  String titleEvent;
}
