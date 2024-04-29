package org.opencelements.atlas.domain;

import java.util.UUID;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Document {

    private final UUID id;
    private final List<DataObject> objects;

}
