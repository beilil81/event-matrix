package com.humanup.eventmatrix.vo;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(of = {"titleEvent"})
public class TypeEventsVO implements Serializable {
  String titleEvent;
}
