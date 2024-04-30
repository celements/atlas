package org.opencelements.atlas.services;

import java.util.UUID;

import org.opencelements.atlas.exceptions.DocumentCreationException;
import org.springframework.stereotype.Service;

@Service
public class DocumentCreationService {


  public UUID create() throws DocumentCreationException {
    // TODO: Implement this method
    if(false) {
      throw new DocumentCreationException();
    } else{
      return UUID.randomUUID();
    }
  }

}
