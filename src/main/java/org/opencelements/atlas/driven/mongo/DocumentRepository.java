package org.opencelements.atlas.driven.mongo;

import org.opencelements.atlas.domain.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<Document, String> {

}
