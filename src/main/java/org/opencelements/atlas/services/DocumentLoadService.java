package org.opencelements.atlas.services;

import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.exceptions.DocumentNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;

@Service
public class DocumentLoadService {

  private final StoreService store;

  @Inject
  public DocumentLoadService(StoreService store) {
    this.store = store;
  }

  public Document load(String id) throws DocumentNotFoundException{
  return store.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
  }

}
