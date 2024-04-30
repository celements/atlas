package org.opencelements.atlas.driving.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class DocumentCreationResponse {
  
  private UUID id;
  private boolean successful;
  private String errormessage;
    
}
