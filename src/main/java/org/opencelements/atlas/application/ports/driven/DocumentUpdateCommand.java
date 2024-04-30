package org.opencelements.atlas.application.ports.driven;

import org.opencelements.atlas.application.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.application.exceptions.DocumentUpdateException;
import org.opencelements.atlas.application.model.AtlasDocument;

import jakarta.validation.constraints.NotNull;

public interface DocumentUpdateCommand {

  public void update(@NotNull AtlasDocument document)
      throws DocumentNotFoundException, DocumentUpdateException;

}
