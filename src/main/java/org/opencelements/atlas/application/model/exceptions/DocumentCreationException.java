package org.opencelements.atlas.application.model.exceptions;

public class DocumentCreationException extends Exception {

  public DocumentCreationException(String reason) {
    super("Document could not be created: " + reason);
  }
}
