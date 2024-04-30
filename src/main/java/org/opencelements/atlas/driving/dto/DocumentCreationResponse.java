package org.opencelements.atlas.driving.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class DocumentCreationResponse {
  
  private UUID id;
  private boolean successful;
  private String errormessage;
    
}
