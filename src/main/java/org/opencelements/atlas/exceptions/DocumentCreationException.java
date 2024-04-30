package org.opencelements.atlas.exceptions;

public class DocumentCreationException extends RuntimeException {

  public DocumentCreationException(){
    super("Document could not be created.");
  }
}
