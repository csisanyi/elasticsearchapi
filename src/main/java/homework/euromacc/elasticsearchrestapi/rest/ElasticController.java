package homework.euromacc.elasticsearchrestapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/elasticsearch")
public class ElasticController {

    @Autowired
    Services services;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest request) throws IOException {
        return services.create(request);
    }

    @PostMapping("/search")
    public ResponseEntity<UserSearchResponse> search(@RequestBody UserSearchRequest request) throws IOException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return services.search(request);
    }

}

