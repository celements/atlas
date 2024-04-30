package org.opencelements.atlas.driving.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
public class DocumentDto {

      private final UUID id;
      private final List<DataObjectDto> objects;

}
