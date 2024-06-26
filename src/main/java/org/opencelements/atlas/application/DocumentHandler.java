package org.opencelements.atlas.application;

import org.opencelements.atlas.application.model.AtlasDocument;
import org.opencelements.atlas.application.model.exceptions.DocumentCreationException;
import org.opencelements.atlas.application.model.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.application.model.exceptions.DocumentUpdateException;
import org.opencelements.atlas.application.ports.driven.DocumentStore;
import org.opencelements.atlas.application.ports.driving.DocumentCreateCommand;
import org.opencelements.atlas.application.ports.driving.DocumentLoadCommand;
import org.opencelements.atlas.application.ports.driving.DocumentUpdateCommand;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Service
public class DocumentHandler
    implements DocumentLoadCommand, DocumentCreateCommand, DocumentUpdateCommand {

  private final DocumentStore store;

  @Inject
  public DocumentHandler(DocumentStore store) {
    this.store = store;
  }

  @Override
  public AtlasDocument load(String id) throws DocumentNotFoundException {
    return store.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
  }

  @Override
  public @NotEmpty String create(@NotNull AtlasDocument document)
      throws DocumentCreationException {
    return store.create(document);
  }

  @Override
  public void update(@NotNull AtlasDocument document)
      throws DocumentNotFoundException, DocumentUpdateException {
    store.update(document);
  }

}
