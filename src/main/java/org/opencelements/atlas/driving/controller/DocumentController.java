package org.opencelements.atlas.driving.controller;

import org.springframework.web.bind.annotation.RestController;

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
            docCreateionResp.successful = true;
        } catch (Exception exp) {
            docCreateionResp.successful = false;
            docCreateionResp.errorMessage = exp.getMessage();
        }
        return docCreationResp;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentCreationResponse get(@PathVariable UUID id) {
        return docLoadSrv.load(id);
    }

}