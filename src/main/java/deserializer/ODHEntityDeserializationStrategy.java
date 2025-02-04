package deserializer;

import dao.ODHEntity;

import java.net.http.HttpResponse;

public interface ODHEntityDeserializationStrategy {
    ODHEntity deserialize(HttpResponse<String> response);
}
