package org.opencelements.atlas.adapters.driving.mapper;

import org.mapstruct.Mapper;
import org.opencelements.atlas.adapters.driving.model.DocumentDto;
import org.opencelements.atlas.adapters.driving.model.ObjectDto;
import org.opencelements.atlas.application.model.AtlasObject;
import org.opencelements.atlas.application.model.AtlasDocument;

@Mapper(componentModel = "spring")
public interface DrivingMapper {

  AtlasDocument toDocument(DocumentDto docDto);

  DocumentDto toDocumentDto(AtlasDocument doc);

  AtlasObject toDataObject(ObjectDto objDto);

  ObjectDto toDataObjectDto(AtlasObject obj);
}
