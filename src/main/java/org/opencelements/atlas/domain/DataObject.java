package org.opencelements.atlas.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DataObject {

    private final UUID id;
    private final Document doc;

}
