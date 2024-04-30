package org.opencelements.atlas.application.ports.driven;

import org.opencelements.atlas.application.exceptions.DocumentCreationException;
import org.opencelements.atlas.application.model.AtlasDocument;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface DocumentCreateCommand {

  @NotEmpty
  String create(@NotNull AtlasDocument document) throws DocumentCreationException;

}
