package org.opencelements.atlas.application.ports.driven;

import org.opencelements.atlas.application.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.application.model.AtlasDocument;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface DocumentLoadCommand {

  @NotNull
  AtlasDocument load(@NotEmpty String id) throws DocumentNotFoundException;

}
