import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.opencelements.atlas.domain.DataObject;
import org.opencelements.atlas.domain.Document;

@Getter
@Builder
public class DataObject {

    private final UUID id;
    private final Document doc;

}