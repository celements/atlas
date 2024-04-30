package org.opencelements.atlas.application.ports.driving;

import java.util.List;
import java.util.Optional;

import org.opencelements.atlas.application.exceptions.DocumentCreationException;
import org.opencelements.atlas.application.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.application.exceptions.DocumentUpdateException;
import org.opencelements.atlas.application.model.AtlasDocument;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface DocumentStore {

  List<AtlasDocument> findAll();

  Optional<AtlasDocument> findById(@NotEmpty String id);

  long count();

  @NotEmpty
  String create(@NotNull AtlasDocument document)
      throws DocumentCreationException;

  void update(@NotNull AtlasDocument document)
      throws DocumentNotFoundException, DocumentUpdateException;

}
