package hackathon.spring.repository;

import hackathon.spring.domain.Review;
import hackathon.spring.domain.enums.Sun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    List<Review> findBySunEvent(Sun sun);
}
