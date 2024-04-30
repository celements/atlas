package org.opencelements.atlas.adapters.driven.mongo;

import org.opencelements.atlas.adapters.driven.model.StoreDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<StoreDocument, String> {

}
