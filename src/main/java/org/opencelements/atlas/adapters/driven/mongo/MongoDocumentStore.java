package org.opencelements.atlas.adapters.driven.mongo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.opencelements.atlas.adapters.driven.model.StoreDocument;
import org.opencelements.atlas.adapters.driven.model.StoreObject;
import org.opencelements.atlas.application.model.AtlasDocument;
import org.opencelements.atlas.application.model.AtlasObject;
import org.opencelements.atlas.application.model.exceptions.DocumentCreationException;
import org.opencelements.atlas.application.model.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.application.model.exceptions.DocumentUpdateException;
import org.opencelements.atlas.application.ports.driven.DocumentStore;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;

@Service
public class MongoDocumentStore implements DocumentStore {

  private final DocumentRepository docRepository;
  private final DocumentObjectRepository objRepository;

  @Inject
  public MongoDocumentStore(
      DocumentRepository docRepository,
      DocumentObjectRepository objRepository) {
    this.docRepository = docRepository;
    this.objRepository = objRepository;
  }

  @Override
  public List<AtlasDocument> findAll() {
    var stream = docRepository.findAll().stream();
    return stream.map(docMapper).toList();
  }

  @Override
  public Optional<AtlasDocument> findById(String id) {
    return docRepository.findById(id).map(docMapper);
  }

  @Override
  public long count() {
    return docRepository.count();
  }

  @Override
  public String create(AtlasDocument document) throws DocumentCreationException {
    try {
      var objs = document.getObjects().stream()
          .map(obj -> objRepository.save(StoreObject.builder().data(obj.getData()).build()))
          .toList();
      var doc = docRepository.insert(StoreDocument.builder().objects(objs).build());
      return doc.getId();
    } catch (Exception e) {
      throw new DocumentCreationException(e.getMessage());
    }
  }

  @Override
  public void update(AtlasDocument document)
      throws DocumentNotFoundException, DocumentUpdateException {
    var id = Objects.requireNonNull(document.getId());
    try {
      objRepository.deleteAll(docRepository.findById(id)
          .orElseThrow(() -> new DocumentNotFoundException(id))
          .getObjects());
      var objs = document.getObjects().stream()
          .map(obj -> objRepository.save(StoreObject.builder().data(obj.getData()).build()))
          .toList();
      docRepository.save(StoreDocument.builder().id(id).objects(objs).build());
    } catch (Exception e) {
      throw new DocumentUpdateException(id, e.getMessage());
    }
  }

  private final Function<StoreObject, AtlasObject> objMapper = (obj -> AtlasObject.builder()
      .id(obj.getId())
      .data(obj.getData())
      .build());

  private final Function<StoreDocument, AtlasDocument> docMapper = doc -> AtlasDocument.builder()
      .id(doc.getId())
      .objects(doc.getObjects().stream().map(objMapper).toList())
      .build();

}
