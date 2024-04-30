package org.opencelements.atlas.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class DataObject {

    private final String id;
    private final org.bson.Document data;

}
