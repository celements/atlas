package org.opencelements.atlas.adapters.driven.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document(collection = "objects")
public class StoreObject {

  @Id
  private final String id;
  private final org.bson.Document data;

}
