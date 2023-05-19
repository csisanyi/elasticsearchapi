package homework.euromacc.elasticsearchrestapi.rest;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "users")
public class CreateUserRequest {
    @Id
    @Generated
    String id;
    String firstName;
    String lastName;
    String email;

    public CreateUserRequest(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
