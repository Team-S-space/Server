package hackathon.spring.service.UserService;

import hackathon.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    @Override
    public boolean isUserExist(Long id) {
        return userRepository.existsById(id);
    }
}
