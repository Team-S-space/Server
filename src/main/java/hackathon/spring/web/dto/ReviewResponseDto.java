package hackathon.spring.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hackathon.spring.domain.enums.Sun;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReviewDetailDto {
        String title;
        String address;
        String imageUrl;
        Sun sunEvent;
        Boolean isAdmin;

        @JsonFormat(pattern = "HH:mm")
        LocalDateTime sunriseTime;

        @JsonFormat(pattern = "HH:mm")
        LocalDateTime sunsetTime;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime created_at;
    }
}
