package org.opencelements.atlas.adapters.driving.model;

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
