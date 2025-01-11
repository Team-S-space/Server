package hackathon.spring.service.reviewservice;

import hackathon.spring.web.dto.ReviewResponseDto;

public interface ReviewQueryService {
    ReviewResponseDto.ReviewDetailDto getReview(Long reviewId);
}
