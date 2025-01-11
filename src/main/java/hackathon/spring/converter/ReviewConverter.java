package hackathon.spring.converter;

import hackathon.spring.domain.Review;
import hackathon.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewDetailDTO toReviewDetailResultDTO(Review review, Map<String, LocalDateTime> sunInfo) {

        return ReviewResponseDTO.ReviewDetailDTO.builder()
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

    public static ReviewResponseDTO.ReviewListDTO toReviewListDTO(List<ReviewResponseDTO.ReviewDetailDTO> reviews, boolean hasNext, Long lastId) {

        return ReviewResponseDTO.ReviewListDTO.builder()
                .reviews(reviews)
                .hasNext(hasNext)
                .lastId(lastId)
                .build();
    }
}
