package org.opencelements.atlas.driving.controller;

import java.util.List;

import org.opencelements.atlas.driving.dto.DocumentDto;
import org.opencelements.atlas.driving.mapper.DrivingMapper;
import org.opencelements.atlas.exceptions.DocumentCreationException;
import org.opencelements.atlas.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.exceptions.DocumentUpdateException;
import org.opencelements.atlas.services.DocumentWriteService;
import org.opencelements.atlas.services.DocumentLoadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

  private final DocumentWriteService writeService;
  private final DocumentLoadService loadService;
  private final DrivingMapper mapper;

  @Inject
  public DocumentController(
      DocumentWriteService createService,
      DocumentLoadService loadService,
      DrivingMapper mapper) {
    this.writeService = createService;
    this.loadService = loadService;
    this.mapper = mapper;
  }

  @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestBody List<org.bson.Document> objectData)
      throws DocumentCreationException {
    return writeService.create(objectData);
  }

  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public void update(
      @PathVariable String id,
      @RequestBody List<org.bson.Document> objectData)
      throws DocumentNotFoundException, DocumentUpdateException {
    writeService.update(id, objectData);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public DocumentDto get(@PathVariable String id) throws DocumentNotFoundException {
    var doc = loadService.load(id);
    return mapper.toDocumentDto(doc);
  }

  @ExceptionHandler(DocumentNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String documentNotFoundHandler(DocumentNotFoundException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(DocumentCreationException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  String documentNotCreatedHandler(DocumentCreationException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(DocumentUpdateException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  String documentNotUpdatedHandler(DocumentUpdateException ex) {
    return ex.getMessage();
  }
}
