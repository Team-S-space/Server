package hackathon.spring.service.reviewservice;

import hackathon.spring.web.dto.ReviewResponseDTO;

public interface ReviewQueryService {
    ReviewResponseDTO.ReviewDetailDTO getReview(Long reviewId);
}
