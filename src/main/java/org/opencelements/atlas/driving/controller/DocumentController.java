package org.opencelements.atlas.driving.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.inject.Inject;
import java.util.UUID;
import java.util.stream.Collectors;

import org.opencelements.atlas.services.DocumentCreationService;
import org.opencelements.atlas.services.DocumentLoadService;
import org.springframework.http.HttpStatus;
import org.opencelements.atlas.driving.dto.DataObjectDto;
import org.opencelements.atlas.driving.dto.DocumentCreationResponse;
import org.opencelements.atlas.driving.dto.DocumentDto;


@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentCreationService docCreateSrv;
    private final DocumentLoadService docLoadSrv;

    @Inject
    public DocumentController(
        DocumentCreationService docCreateSrv, 
        DocumentLoadService docLoadSrv) {
        this.docCreateSrv = docCreateSrv;
        this.docLoadSrv = docLoadSrv;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentCreationResponse create() {
        var docCreationResp = new DocumentCreationResponse();
        try {
            docCreationResp.setId(docCreateSrv.create());
            docCreationResp.setSuccessful(true);
        } catch (Exception exp) {
            docCreationResp.setSuccessful(false);
            docCreationResp.setErrormessage(exp.getMessage());
        }
        return docCreationResp;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentDto get(@PathVariable UUID id) {
        var doc = docLoadSrv.load(id);
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
