package hackathon.spring.converter;

import hackathon.spring.domain.Review;
import hackathon.spring.domain.enums.Sun;
import hackathon.spring.web.dto.Review.ReviewRequestDTO;
import hackathon.spring.web.dto.Review.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.Map;

public class ReviewConverter {
    public static hackathon.spring.web.dto.ReviewResponseDTO.ReviewDetailDTO toReviewDetailResultDTO(Review review, Map<String, LocalDateTime> sunInfo) {

        return hackathon.spring.web.dto.ReviewResponseDTO.ReviewDetailDTO.builder()
                .title(review.getTitle())
                .address(review.getLocation().getAddress())
                .imageUrl(review.getImageUrl())
                .sunEvent(review.getSunEvent())
                .isAdmin(review.getUser().getIsAdmin())
                .sunriseTime(sunInfo.get("sunrise"))
                .sunsetTime(sunInfo.get("sunset"))
                .created_at(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.addReviewResultDTO toAddReviewResultDTO(Review review) {
        return ReviewResponseDTO.addReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.addReviewDTO request, String imageUrl) {
        Sun sun = null;
        switch (request.getSunEvent()){
            case 0:
                sun = Sun.SUNRISE;
                break;
            case 1:
                sun = Sun.SUNSET;
                break;
        }

        return Review.builder()
                .title(request.getTitle())
                .imageUrl(imageUrl)
                .sunEvent(sun)
                .build();
    }
}
