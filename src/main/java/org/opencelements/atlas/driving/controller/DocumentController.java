package org.opencelements.atlas.driving.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.inject.Inject;
import java.util.UUID;

import org.opencelements.atlas.driving.dto.DocumentCreationService;
import org.opencelements.atlas.driving.dto.DocumentLoadService;
import org.springframework.http.HttpStatus;

import org.opencelements.atlas.driving.dto.DocumentCreationResponse;


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
            docCreationResp.id = docCreateSrv.create();
            docCreationResp.successful = true;
        } catch (Exception exp) {
            docCreationResp.successful = false;
            docCreationResp.errorMessage = exp.getMessage();
        }
        return docCreationResp;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentCreationResponse get(@PathVariable UUID id) {
        return docLoadSrv.load(id);
    }

}
