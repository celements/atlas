package org.opencelements.atlas.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document(collection = "document")
public class Document {

    private final String id;
    private final List<DataObject> objects;

}
