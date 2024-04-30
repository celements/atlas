package org.opencelements.atlas.services;

import java.util.List;
import java.util.function.Function;

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
    var stream = docRepository.findAll().stream();
    Function<StoreObject, DataObject> objMapper = (obj -> DataObject.builder()
        .id(obj.getId())
        .data(obj.getData())
        .build());
    Function<StoreDocument, Document> docMapper = doc -> Document.builder()
        .id(doc.getId())
        .objects(doc.getObjects().stream().map(objMapper).toList())
        .build();
    return stream.map(docMapper).toList();
  }

}
