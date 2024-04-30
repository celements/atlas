package org.opencelements.atlas.driven.mongo;

import org.opencelements.atlas.driven.model.StoreObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataObjectRepository extends MongoRepository<StoreObject, String> {

}
