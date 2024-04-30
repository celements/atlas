package org.opencelements.atlas.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.driven.model.StoreDocument;
import org.opencelements.atlas.driven.model.StoreObject;
import org.opencelements.atlas.driven.mongo.DataObjectRepository;
import org.opencelements.atlas.driven.mongo.DocumentRepository;
import org.opencelements.atlas.exceptions.DocumentCreationException;
import org.opencelements.atlas.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.exceptions.DocumentUpdateException;
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
    try {
      var objs = objectData.stream()
          .map(obj -> objRepository.save(StoreObject.builder().data(obj.getData()).build()))
          .toList();
      var doc = docRepository.save(StoreDocument.builder().objects(objs).build());
      return doc.getId();
    } catch (Exception e) {
      throw new DocumentCreationException(e.getMessage());
    }
  }

  public void update(String id, List<DataObject> objectData)
      throws DocumentNotFoundException, DocumentUpdateException {
    try {
      var doc = docRepository.findById(id)
          .orElseThrow(() -> new DocumentNotFoundException(id));
      objRepository.deleteAll(doc.getObjects());
      var objs = objectData.stream()
          .map(obj -> objRepository.save(StoreObject.builder().data(obj.getData()).build()))
          .toList();
      docRepository.save(doc.toBuilder().objects(objs).build());
    } catch (Exception e) {
      throw new DocumentUpdateException(id, e.getMessage());
    }
  }

  public long count() {
    return docRepository.count();
  }

  public Optional<Document> findById(String id) {
    return docRepository.findById(id).map(docMapper);
  }

  public List<Document> findAll() {
    var stream = docRepository.findAll().stream();
    return stream.map(docMapper).toList();
  }

  private final Function<StoreObject, DataObject> objMapper = (obj -> DataObject.builder()
      .id(obj.getId())
      .data(obj.getData())
      .build());

  private final Function<StoreDocument, Document> docMapper = doc -> Document.builder()
      .id(doc.getId())
      .objects(doc.getObjects().stream().map(objMapper).toList())
      .build();

}
