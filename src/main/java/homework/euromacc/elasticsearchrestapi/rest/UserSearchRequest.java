package homework.euromacc.elasticsearchrestapi.rest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchRequest {

    String firstName;
    String lastName;
}
