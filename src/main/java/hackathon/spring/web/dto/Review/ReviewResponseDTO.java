package hackathon.spring.web.dto.Review;

import com.fasterxml.jackson.annotation.JsonFormat;
import hackathon.spring.domain.enums.Sun;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addReviewResultDTO {
        Long reviewId;
        LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReviewDetailDTO {
        Long reviewId;
        String title;
        String address;
        String imageUrl;
        Integer sunEvent;
        Boolean isAdmin;

        @JsonFormat(pattern = "HH:mm")
        LocalDateTime sunriseTime;

        @JsonFormat(pattern = "HH:mm")
        LocalDateTime sunsetTime;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime created_at;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReviewListDTO {
        List<ReviewResponseDTO.ReviewDetailDTO> reviews;
        boolean hasNext;
        Long lastId;
    }
}
