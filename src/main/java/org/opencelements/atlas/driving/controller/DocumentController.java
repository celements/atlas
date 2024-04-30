package org.opencelements.atlas.driving.controller;

import java.util.List;
import java.util.UUID;
import org.opencelements.atlas.driving.dto.DocumentCreationResponse;
import org.opencelements.atlas.driving.dto.DocumentDto;
import org.opencelements.atlas.exceptions.DocumentCreationException;
import org.opencelements.atlas.exceptions.DocumentNotFoundException;
import org.opencelements.atlas.mapper.Mapper;
import org.opencelements.atlas.services.DocumentCreationService;
import org.opencelements.atlas.services.DocumentLoadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.inject.Inject;


@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentCreationService createService;
    private final DocumentLoadService loadService;
    private final Mapper mapper;

    @Inject
    public DocumentController(
        DocumentCreationService createService, 
        DocumentLoadService loadService,
        Mapper mapper) {
      this.createService = createService;
      this.loadService = loadService;
      this.mapper = mapper;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody List<Object> objectData) {
        return createService.create(objectData);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentDto get(@PathVariable String id) {
      var doc = loadService.load(id);
      return mapper.toDocumentDto(doc);
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String documentNotFoundHandler(DocumentNotFoundException ex) {
      return ex.getMessage();
    }

    @ExceptionHandler(DocumentCreationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String documentNotCreatedHandler(DocumentCreationException ex) {
      return ex.getMessage();
    }
}
