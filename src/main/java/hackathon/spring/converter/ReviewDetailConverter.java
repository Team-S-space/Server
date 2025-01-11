package hackathon.spring.converter;

import hackathon.spring.domain.Review;
import hackathon.spring.web.dto.ReviewDetailResponseDto;

import java.time.LocalDateTime;
import java.util.Map;

public class ReviewDetailConverter {

    public static ReviewDetailResponseDto toReviewDetailResponseDto(Review review, Map<String, LocalDateTime> sunInfo) {


        return ReviewDetailResponseDto.builder()
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
