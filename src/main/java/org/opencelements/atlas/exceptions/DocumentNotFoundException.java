package org.opencelements.atlas.exceptions;

public class DocumentNotFoundException extends Exception {

  public DocumentNotFoundException(String id) {
    super("Document [" + id + "] not found");
  }

}
