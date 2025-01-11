package hackathon.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class UserRequestDTO {
    @Getter
    public static class JoinDTO{
        @NotBlank
        String userId;
        @NotBlank
        String password;
    }

    @Getter
    public static class LoginDTO{
        @NotBlank
        String userId;
        @NotBlank
        String password;
    }
}
