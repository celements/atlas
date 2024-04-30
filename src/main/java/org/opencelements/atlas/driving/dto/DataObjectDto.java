package org.opencelements.atlas.driving.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
public class DataObjectDto {

  private final String id;
  private final org.bson.Document data;

}
