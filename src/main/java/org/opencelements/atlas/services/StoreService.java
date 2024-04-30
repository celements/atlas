package org.opencelements.atlas.services;

import java.util.List;

import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.driven.model.StoreDocument;
import org.opencelements.atlas.driven.model.StoreObject;
import org.opencelements.atlas.driven.mongo.DataObjectRepository;
import org.opencelements.atlas.driven.mongo.DocumentRepository;
import org.opencelements.atlas.exceptions.DocumentCreationException;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;

@Service
public class StoreService {

  private final DocumentRepository docRepository;
  private final DataObjectRepository objRepository;

  @Inject
  public StoreService(
      DocumentRepository docRepository,
      DataObjectRepository objRepository) {
    this.docRepository = docRepository;
    this.objRepository = objRepository;
  }

  public String create(List<DataObject> objectData) throws DocumentCreationException {
    var objs = objectData.stream()
        .map(obj -> objRepository.save(StoreObject.builder().data(obj.getData()).build()))
        .toList();
    var doc = docRepository.save(StoreDocument.builder().objects(objs).build());
    return doc.getId();
  }

  public long count() {
    return docRepository.count();
  }

  public List<Document> findAll() {
    return docRepository.findAll().stream()
        .map(doc -> Document.builder()
            .objects(doc.getObjects().stream()
                .map(obj -> DataObject.builder().data(obj.getData()).build())
                .toList())
            .build()).toList();
  }

}
