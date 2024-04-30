package org.opencelements.atlas.driven.mongo;

import org.opencelements.atlas.driven.model.StoreDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<StoreDocument, String> {

}
