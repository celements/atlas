package org.opencelements.atlas.driving.controller;

import java.util.UUID;
import org.opencelements.atlas.driving.dto.DocumentCreationResponse;
import org.opencelements.atlas.driving.dto.DocumentDto;
import org.opencelements.atlas.mapper.Mapper;
import org.opencelements.atlas.services.DocumentCreationService;
import org.opencelements.atlas.services.DocumentLoadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.inject.Inject;


@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentCreationService docCreateSrv;
    private final DocumentLoadService docLoadSrv;
    private final Mapper mapper;

    @Inject
    public DocumentController(
        DocumentCreationService docCreateSrv, 
        DocumentLoadService docLoadSrv,
        Mapper mapper) {
        this.docCreateSrv = docCreateSrv;
        this.docLoadSrv = docLoadSrv;
        this.mapper = mapper;
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
        return mapper.toDocumentDto(doc);
    }
}
