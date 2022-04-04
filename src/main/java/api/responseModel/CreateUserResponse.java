package api.responseModel;

import lombok.Data;

@Data
public class CreateUserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
