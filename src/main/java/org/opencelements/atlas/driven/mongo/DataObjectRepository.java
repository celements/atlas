package org.opencelements.atlas.driven.mongo;

import java.util.Optional;
import java.util.UUID;

import org.opencelements.atlas.domain.DataObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataObjectRepository extends MongoRepository<DataObject, UUID> {

  public Optional<DataObject> findById(UUID id);

  public void deleteById(UUID id);

  public void deleteAll();

  public boolean existsById(UUID id);

  public long count();

  public DataObject save(DataObject entity);

}
