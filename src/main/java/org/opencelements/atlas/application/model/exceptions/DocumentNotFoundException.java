package org.opencelements.atlas.application.model.exceptions;

public class DocumentNotFoundException extends Exception {

  public DocumentNotFoundException(String id) {
    super("Document [" + id + "] not found");
  }

}
