package hackathon.spring.service.reviewservice;

import hackathon.spring.web.dto.ReviewDetailResponseDto;

public interface ReviewQueryService {
    ReviewDetailResponseDto getReview(Long reviewId);
}
