package org.opencelements.atlas.application.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class AtlasDocument {

    private final String id;
    private final List<AtlasObject> objects;

}
