package org.opencelements.atlas.driving.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DocumentCreationResponse {
  
  private final UUID id;
  private boolean successful;
  private String errormessage;
    
}
