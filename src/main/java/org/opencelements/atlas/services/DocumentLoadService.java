package org.opencelements.atlas.services;

import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.driven.mongo.DataObjectRepository;
import org.opencelements.atlas.driven.mongo.DocumentRepository;
import org.opencelements.atlas.exceptions.DocumentNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;

@Service
public class DocumentLoadService {

  private final DocumentRepository docRepository;
  private final DataObjectRepository objRepository;

  @Inject
  public DocumentLoadService(
      DocumentRepository docRepository,
      DataObjectRepository objRepository) {
    this.docRepository = docRepository;
    this.objRepository = objRepository;
  }

  public Document load(String id) throws DocumentNotFoundException{
  return docRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
  }

}
