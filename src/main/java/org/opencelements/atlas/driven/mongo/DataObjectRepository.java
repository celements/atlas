package org.opencelements.atlas.driven.mongo;

import org.opencelements.atlas.domain.DataObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataObjectRepository extends MongoRepository<DataObject, String> {

}
