package org.opencelements.atlas.driving.mapper;

import org.mapstruct.Mapper;
import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.driving.dto.DataObjectDto;
import org.opencelements.atlas.driving.dto.DocumentDto;

@Mapper(componentModel = "spring")
public interface DrivingMapper {

  Document toDocument(DocumentDto docDto);

  DocumentDto toDocumentDto(Document doc);

  DataObject toDataObject(DataObjectDto objDto);

  DataObjectDto toDataObjectDto(DataObject obj);
}
