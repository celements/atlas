package org.opencelements.atlas.adapters.driving.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
public class DocumentDto {

  private final String id;
  private final List<ObjectDto> objects;

}
