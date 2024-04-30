package org.opencelements.atlas.adapters.driven.mongo;

import org.opencelements.atlas.adapters.driven.model.StoreObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataObjectRepository extends MongoRepository<StoreObject, String> {

}
