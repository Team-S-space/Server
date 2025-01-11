package hackathon.spring.web.dto.Review;

import lombok.Getter;

public class ReviewRequestDTO {
    @Getter
    public static class addReviewDTO {
        Long userId;
        String latitude;
        String longitude;
        String title;
        Integer sunEvent;
    }
}
