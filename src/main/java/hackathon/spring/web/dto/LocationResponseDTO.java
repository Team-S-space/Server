package hackathon.spring.web.dto;

import hackathon.spring.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class LocationResponseDTO {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class getLocationDTO {
        Long reviewId;
        String latitude;
        String longitude;
    }
}
