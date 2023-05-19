package homework.euromacc.elasticsearchrestapi.rest;

import co.elastic.clients.elasticsearch.core.IndexResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@NoArgsConstructor
public class Services {

    @Autowired
    ElasticRepo elasticRepo;

    public ResponseEntity<UserResponse> create(CreateUserRequest request) throws IOException {
        IndexResponse indexResponse = elasticRepo.create(request);
        return ResponseEntity.ok(new UserResponse(indexResponse.id(), request.firstName, request.lastName, request.email));
    }

    public ResponseEntity<UserSearchResponse> search(UserSearchRequest request) throws IOException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return ResponseEntity.ok(elasticRepo.search(request));
    }

}
