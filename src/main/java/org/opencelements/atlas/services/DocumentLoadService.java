package org.opencelements.atlas.services;

import org.opencelements.atlas.domain.Document;
import org.springframework.stereotype.Service;

@Service
public class DocumentLoadService {

  public Document load(String id) {
    // TODO: Implement this method
    return Document.builder().id(id).build();
  }

}
