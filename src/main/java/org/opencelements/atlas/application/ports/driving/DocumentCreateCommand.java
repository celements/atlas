package org.opencelements.atlas.application.ports.driving;

import org.opencelements.atlas.application.model.AtlasDocument;
import org.opencelements.atlas.application.model.exceptions.DocumentCreationException;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface DocumentCreateCommand {

  @NotEmpty
  String create(@NotNull AtlasDocument document) throws DocumentCreationException;

}
