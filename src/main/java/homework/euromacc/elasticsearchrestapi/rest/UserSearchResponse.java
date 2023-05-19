package homework.euromacc.elasticsearchrestapi.rest;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchResponse {

    long total;
    List<UserResponse> users;
}
