package hackathon.spring.converter;

import hackathon.spring.domain.Review;
import hackathon.spring.web.dto.ReviewResponseDto;

import java.time.LocalDateTime;
import java.util.Map;

public class ReviewDetailConverter {

    public static ReviewResponseDto.ReviewDetailDto toReviewDetailResponseDto(Review review, Map<String, LocalDateTime> sunInfo) {


        return ReviewResponseDto.ReviewDetailDto.builder()
                .title(review.getTitle())
                .address(review.getLocation().getAddress())
                .imageUrl(review.getImage_url())
                .sunEvent(review.getSun_event())
                .isAdmin(review.getUser().getIsAdmin())
                .sunriseTime(sunInfo.get("sunrise"))
                .sunsetTime(sunInfo.get("sunset"))
                .created_at(review.getCreatedAt())
                .build();
    }
}
