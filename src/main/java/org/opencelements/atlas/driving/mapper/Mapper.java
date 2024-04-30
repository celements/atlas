package org.opencelements.atlas.driving.mapper;

import java.util.stream.Collectors;

import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.domain.Document;
import org.opencelements.atlas.driving.dto.DataObjectDto;
import org.opencelements.atlas.driving.dto.DocumentDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

  public Document toDocument(DocumentDto docDto) {
    return Document.builder()
      .id(docDto.getId())
      .objects(docDto.getObjects().stream()
        .map(this::toDataObject)
        .collect(Collectors.toList()))
      .build();
  }

  public DocumentDto toDocumentDto(Document doc) {
    return DocumentDto.builder()
      .id(doc.getId())
      .objects(doc.getObjects().stream()
        .map(this::toDataObjectDto)
        .collect(Collectors.toList()))
      .build();
  }

  public DataObject toDataObject(DataObjectDto objDto) {
    return DataObject.builder()
      .id(objDto.getId())
      .data(objDto.getData())
      .build();
  }

  public DataObjectDto toDataObjectDto(DataObject obj) {
    return DataObjectDto.builder()
      .id(obj.getId())
      .data(obj.getData())
      .build();
  }

}
