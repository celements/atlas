package org.opencelements.atlas.driving.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public class DocumentDto {

      private final UUID id;
      private final List<DataObjectDto> objects;


}
