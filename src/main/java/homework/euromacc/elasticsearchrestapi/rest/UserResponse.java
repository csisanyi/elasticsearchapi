package homework.euromacc.elasticsearchrestapi.rest;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    String uuid;
    @Field(name = "first_name", type = FieldType.Text)
    String firstName;
    @Field(name = "last_name", type = FieldType.Text)
    String lastName;
    @Field(name = "email", type = FieldType.Text)
    String email;
}
