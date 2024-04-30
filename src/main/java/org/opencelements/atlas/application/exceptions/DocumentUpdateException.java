package org.opencelements.atlas.application.exceptions;

public class DocumentUpdateException extends Exception {

  public DocumentUpdateException(String id, String reason) {
    super("Document [" + id + "] could not be updated: " + reason);
  }
}
