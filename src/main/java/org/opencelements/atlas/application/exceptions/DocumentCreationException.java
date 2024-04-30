package org.opencelements.atlas.application.exceptions;

public class DocumentCreationException extends Exception {

  public DocumentCreationException(String reason) {
    super("Document could not be created: " + reason);
  }
}
