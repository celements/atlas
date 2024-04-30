package org.opencelements.atlas.services;

import java.util.List;

import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.exceptions.DocumentCreationException;
import org.opencelements.atlas.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.exceptions.DocumentUpdateException;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;

@Service
public class DocumentWriteService {

  private final StoreService storeService;

  @Inject
  public DocumentWriteService(StoreService storeService) {
    this.storeService = storeService;
  }

  public String create(List<org.bson.Document> objectData) throws DocumentCreationException {
    return storeService.create(convert(objectData));
  }

  public void update(String id, List<org.bson.Document> objectData)
      throws DocumentNotFoundException, DocumentUpdateException {
    storeService.update(id, convert(objectData));
  }

  private List<DataObject> convert(List<org.bson.Document> objectData) {
    return objectData.stream()
        .map(obj -> DataObject.builder().data(obj).build())
        .toList();
  }

}
