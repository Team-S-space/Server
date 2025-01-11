package hackathon.spring.converter;

import hackathon.spring.domain.Location;
import hackathon.spring.web.dto.Review.ReviewRequestDTO;

public class LocationConverter {
    public static Location toLocation(ReviewRequestDTO.addReviewDTO request, String address) {
        return Location.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .address(address)
                .build();
    }
}
