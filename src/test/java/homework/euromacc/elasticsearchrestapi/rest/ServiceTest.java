package homework.euromacc.elasticsearchrestapi.rest;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceTest {

    private Services services;

    @Mock
    private ElasticRepo elasticRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        services = new Services();
        services.elasticRepo = elasticRepo;
    }

    @Test
    public void testCreate() throws IOException {
        // Arrange
        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");

        IndexResponse indexResponse = mock(IndexResponse.class);
        when(indexResponse.id()).thenReturn("123");
        when(indexResponse.version()).thenReturn(1L);
        when(indexResponse.result()).thenReturn(Result.Created);


        when(elasticRepo.create(request)).thenReturn((IndexResponse) indexResponse);

        // Act
        ResponseEntity<UserResponse> responseEntity = services.create(request);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        UserResponse response = responseEntity.getBody();
        assertEquals("123", response.getUuid());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("john.doe@example.com", response.getEmail());

        // Verify mock interactions
        verify(elasticRepo, times(1)).create(request);
    }

    @Test
    public void testSearch() throws IOException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // Arrange
        UserSearchRequest request = new UserSearchRequest();

        UserSearchResponse searchResponse = new UserSearchResponse();
        when(elasticRepo.search(request)).thenReturn(searchResponse);

        // Act
        ResponseEntity<UserSearchResponse> responseEntity = services.search(request);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(searchResponse, responseEntity.getBody());

        // Verify mock interactions
        verify(elasticRepo, times(1)).search(request);
    }

}