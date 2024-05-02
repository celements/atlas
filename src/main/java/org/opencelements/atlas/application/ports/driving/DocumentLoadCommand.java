package org.opencelements.atlas.application.ports.driving;

import org.opencelements.atlas.application.model.AtlasDocument;
import org.opencelements.atlas.application.model.exceptions.DocumentNotFoundException;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface DocumentLoadCommand {

  @NotNull
  AtlasDocument load(@NotEmpty String id) throws DocumentNotFoundException;

}
