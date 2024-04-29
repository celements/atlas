import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.opencelements.atlas.domain.DataObject;

@Getter
@Builder
public class Document {

    private final UUID id;
    private final ImmutableList<DataObject> objects;

}