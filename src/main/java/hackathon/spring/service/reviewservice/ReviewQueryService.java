package hackathon.spring.service.reviewservice;

import hackathon.spring.web.dto.ReviewResponseDTO;

public interface ReviewQueryService {
    ReviewResponseDTO.ReviewDetailDTO getReview(Long reviewId);
    ReviewResponseDTO.ReviewListDTO getReviewList(String region, Integer sunEvent, Long lastId, int limit);
}
