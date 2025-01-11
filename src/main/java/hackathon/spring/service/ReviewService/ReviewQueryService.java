package hackathon.spring.service.ReviewService;

import hackathon.spring.web.dto.ReviewResponseDTO;

public interface ReviewQueryService {
    ReviewResponseDTO.ReviewDetailDTO getReview(Long reviewId);
}
