package hackathon.spring.repository;

import hackathon.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserId(String userId);
    User findByUserId(String userId);
}
