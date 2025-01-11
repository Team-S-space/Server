package hackathon.spring.service.ReviewService;

import hackathon.spring.apiPayload.code.status.ErrorStatus;
import hackathon.spring.apiPayload.exception.handler.UserHandler;
import hackathon.spring.aws.s3.AmazonS3Manager;
import hackathon.spring.converter.LocationConverter;
import hackathon.spring.converter.ReviewConverter;
import hackathon.spring.domain.Location;
import hackathon.spring.domain.Review;
import hackathon.spring.domain.User;
import hackathon.spring.repository.LocationRepository;
import hackathon.spring.repository.ReviewRepository;
import hackathon.spring.repository.UserRepository;
import hackathon.spring.web.dto.Review.ReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AmazonS3Manager s3Manager;
    private final GoogleGeocodingService googleGeocodingService;
    private final LocationRepository locationRepository;

    @Transactional
    @Override
    public Review addReview(ReviewRequestDTO.addReviewDTO request, MultipartFile reviewPicture) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
        String imageUrl = s3Manager.uploadFile(reviewPicture);
        Review newReview = ReviewConverter.toReview(request, imageUrl);

        String address = googleGeocodingService.getFormattedAddress(Double.parseDouble(request.getLatitude()), Double.parseDouble(request.getLongitude()));
        Location location = LocationConverter.toLocation(request, address);

        newReview.setUser(user);
        newReview.setLocation(location);
        locationRepository.save(location);
        return reviewRepository.save(newReview);
    }
}
