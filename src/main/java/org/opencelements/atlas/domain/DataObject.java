package org.opencelements.atlas.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@Document(collection = "objects")
public class DataObject {

    @Id
    private final String id;
    private final org.bson.Document data;

}
