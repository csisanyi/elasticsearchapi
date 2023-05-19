package homework.euromacc.elasticsearchrestapi.rest;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.elastic.clients.elasticsearch.core.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ElasticRepo {
    private static final String INDEX_NAME = "users";

    @Autowired
    private ElasticsearchClient client;


    public IndexResponse create(CreateUserRequest request) throws IOException {


        if (request.getFirstName() == null || request.getLastName() == null) {
            throw new IllegalArgumentException("First name, last name");
        }

        IndexResponse response = client.index(i -> i
                .document(request).index(INDEX_NAME)
        );
        return response;
    }


    public UserSearchResponse search(UserSearchRequest request) throws IOException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(INDEX_NAME)
                .query(q -> q
                        .bool(b -> b
                                .should(sh -> sh
                                        .wildcard(w -> w
                                                .field("firstName")
                                                .value("*"+ request.getFirstName().toLowerCase()+"*")
                                        )
                                )
                                .should(sh -> sh
                                        .wildcard(w -> w
                                                .field("lastName")
                                                .value("*" + request.getLastName().toLowerCase() + "*")
                                        )
                                )
                        )
                ));
        SearchResponse searchResponse = client.search(searchRequest, UserResponse.class);

        UserSearchResponse userSearchResponse = new UserSearchResponse();
        userSearchResponse.setUsers(new ArrayList<>());
        userSearchResponse.setTotal(searchResponse.hits().total().value());

        List<Hit<UserResponse>> hits = searchResponse.hits().hits();

        for(Hit hit : hits) {
            UserResponse userResponse = (UserResponse) hit.source();
            userResponse.setUuid(hit.id());
            userSearchResponse.users.add(userResponse);
        }

        return userSearchResponse;


    }
}


