package org.opencelements.atlas.domain;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Document {

    private final UUID id;
    private final List<DataObject> objects;

}
