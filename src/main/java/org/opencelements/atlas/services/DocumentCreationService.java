package org.opencelements.atlas.services;

import java.util.List;

import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.exceptions.DocumentCreationException;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;

@Service
public class DocumentCreationService {

  private final StoreService storeService;

  @Inject
  public DocumentCreationService(StoreService storeService) {
    this.storeService = storeService;
  }

  public String create(List<org.bson.Document> objectData) throws DocumentCreationException {
    var objs = objectData.stream()
        .map(obj -> DataObject.builder().data(obj).build())
        .toList();
    return storeService.create(objs);
  }

}
