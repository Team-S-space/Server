package hackathon.spring.service.LocationService;

import hackathon.spring.domain.Location;
import hackathon.spring.domain.Review;
import hackathon.spring.domain.enums.Sun;
import hackathon.spring.repository.ReviewRepository;
import hackathon.spring.web.dto.LocationResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {
    private final ReviewRepository reviewRepository;

    public List<LocationResponseDTO.getLocationDTO> getLocationList(Sun sun){
        return reviewRepository.findBySunEvent(sun).stream()
                .map(review -> {
                    Location location = review.getLocation();
                    return LocationResponseDTO.getLocationDTO.builder()
                            .reviewId(review.getId())
                            .latitude(location.getLatitude())
                            .longitude(location.getLongitude())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
