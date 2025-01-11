package hackathon.spring.web.controller;

import hackathon.spring.apiPayload.ApiResponse;
import hackathon.spring.domain.User;
import hackathon.spring.service.UserService.UserService;
import hackathon.spring.web.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API")
    public ApiResponse<User> join(@RequestBody @Valid UserRequestDTO.JoinDTO request){

        return ApiResponse.onSuccess(userService.joinUser(request));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API")
    public ApiResponse<User> join(@RequestBody @Valid UserRequestDTO.LoginDTO request){

        return ApiResponse.onSuccess(userService.loginUser(request));
    }
}
