package hackathon.spring.web.dto.Review;

import hackathon.spring.validation.annotation.ExistUser;
import hackathon.spring.validation.annotation.ValidLatLng;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class ReviewRequestDTO {
    @Getter
    public static class addReviewDTO {
        @ExistUser
        Long userId;

        @ValidLatLng
        String latitude;

        @ValidLatLng
        String longitude;

        @Size(min = 1, max = 25)
        String title;

        @Min(value = 0)
        @Max(value = 1)
        @NotNull
        Integer sunEvent;
    }
}
