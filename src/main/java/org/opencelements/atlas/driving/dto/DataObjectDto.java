package org.opencelements.atlas.driving.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
public class DataObjectDto {

  private final UUID id;

}
