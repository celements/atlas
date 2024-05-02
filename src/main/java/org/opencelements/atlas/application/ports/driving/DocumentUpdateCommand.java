package org.opencelements.atlas.application.ports.driving;

import org.opencelements.atlas.application.model.AtlasDocument;
import org.opencelements.atlas.application.model.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.application.model.exceptions.DocumentUpdateException;

import jakarta.validation.constraints.NotNull;

public interface DocumentUpdateCommand {

  public void update(@NotNull AtlasDocument document)
      throws DocumentNotFoundException, DocumentUpdateException;

}
