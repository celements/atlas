package org.opencelements.atlas.services;

import java.util.List;

import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.driven.mongo.DataObjectRepository;
import org.opencelements.atlas.driven.mongo.DocumentRepository;
import org.opencelements.atlas.exceptions.DocumentCreationException;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;

@Service
public class DocumentCreationService {

  private final DocumentRepository docRepository;
  private final DataObjectRepository objRepository;

  @Inject
  public DocumentCreationService(
      DocumentRepository docRepository,
      DataObjectRepository objRepository) {
    this.docRepository = docRepository;
    this.objRepository = objRepository;
  }


  public String create(List<Object> objectData) throws DocumentCreationException {
    var objs = objectData.stream()
        .map(obj -> objRepository.save(DataObject.builder().data(obj).build()))
        .toList();
    var doc = docRepository.save(Document.builder().objects(objs).build());
    return doc.getId();
  }

}
