package hackathon.spring.repository;

import hackathon.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
//    @Query("SELECT u FROM User u WHERE u.user_id=:user_id")
//    User findByUser_id(@Param("userId") String userId);
}
