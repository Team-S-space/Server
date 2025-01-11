package hackathon.spring.service.ReviewService;

import hackathon.spring.web.dto.Review.ReviewResponseDTO;

public interface ReviewQueryService {
    ReviewResponseDTO.ReviewDetailDTO getReview(Long reviewId);
    ReviewResponseDTO.ReviewListDTO getReviewList(String region, Integer sunEvent, Long lastId, int limit);
}
