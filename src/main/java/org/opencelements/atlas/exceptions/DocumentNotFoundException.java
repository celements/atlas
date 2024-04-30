package org.opencelements.atlas.exceptions;

import java.util.UUID;

public class DocumentNotFoundException extends RuntimeException {

  public DocumentNotFoundException(UUID id){
    super("Document with id " + id + " not found");
  }

}
