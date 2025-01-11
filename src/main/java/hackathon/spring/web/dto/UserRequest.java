package hackathon.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class UserRequest {
    @Getter
    public static class JoinDto{
        @NotBlank
        String userId;
        @NotBlank
        String password;
    }

    @Getter
    public static class LoginDto{
        @NotBlank
        String userId;
        @NotBlank
        String password;
    }
}
