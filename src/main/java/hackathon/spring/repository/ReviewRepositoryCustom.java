package hackathon.spring.repository;

import hackathon.spring.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findAllByRegionWithSunEvent(String region, Integer sunEvent, Long lastId, Pageable pageable);
}
