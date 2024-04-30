package org.opencelements.atlas.driving.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.opencelements.atlas.driving.dto.DataObjectDto;
import org.opencelements.atlas.driving.dto.DocumentCreationResponse;
import org.opencelements.atlas.driving.dto.DocumentDto;
import org.opencelements.atlas.services.DocumentCreationService;
import org.opencelements.atlas.services.DocumentLoadService;
import org.springframework.http.HttpStatus;
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

    @Inject
    public DocumentController(
        DocumentCreationService createService, 
        DocumentLoadService loadService) {
      this.createService = createService;
      this.loadService = loadService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentCreationResponse create(@RequestBody List<Object> objectData) {
      var docCreationResp = new DocumentCreationResponse();
      try {
          docCreationResp.setId(createService.create(objectData));
          docCreationResp.setSuccessful(true);
      } catch (Exception exp) {
          docCreationResp.setSuccessful(false);
          docCreationResp.setErrormessage(exp.getMessage());
      }
      return docCreationResp;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentDto get(@PathVariable String id) {
        var doc = loadService.load(id);
        return DocumentDto.builder()
          .id(doc.getId())
          .objects(doc.getObjects().stream()
            .map(obj -> DataObjectDto.builder()
              .id(obj.getId())
              .build())
            .collect(Collectors.toList()))
          .build();
    }
}
