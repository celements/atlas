package org.opencelements.atlas.services;

import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.exceptions.DocumentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DocumentLoadService {

  public Document load(String id) throws DocumentNotFoundException{
    // TODO: Implement this method
    if(true) {
      throw new DocumentNotFoundException(id);
    }
    return Document.builder().id(id).build();
  }

}
