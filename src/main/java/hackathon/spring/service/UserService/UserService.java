package hackathon.spring.service.UserService;

import hackathon.spring.apiPayload.code.status.ErrorStatus;
import hackathon.spring.apiPayload.exception.handler.TempHandler;
import hackathon.spring.domain.User;
import hackathon.spring.repository.UserRepository;
import hackathon.spring.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User joinUser(UserRequestDTO.JoinDTO request){
        User user = User.builder()
                .userId(request.getUserId())
                .password((request.getPassword()))
                .isAdmin(false)
                .build();

        return userRepository.save(user);
    }

    public User loginUser(UserRequestDTO.LoginDTO request){
        User user = userRepository.findByUserId(request.getUserId());

        if(user == null){
            throw new TempHandler(ErrorStatus.MEMBER_NOT_FOUND);
        } else if (!user.getPassword().equals(request.getPassword())) {
            throw new TempHandler(ErrorStatus.MEMBER_PASSWORD_INCORRECT);
        } else {
            return user;
        }
    }
}
