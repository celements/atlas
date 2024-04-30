package org.opencelements.atlas.driving.dto;

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
  
  private String id;
  private boolean successful;
  private String errormessage;
    
}
