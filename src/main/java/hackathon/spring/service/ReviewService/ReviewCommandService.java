package hackathon.spring.service.ReviewService;

import hackathon.spring.domain.Review;
import hackathon.spring.web.dto.Review.ReviewRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewCommandService {
    Review addReview(ReviewRequestDTO.addReviewDTO request, MultipartFile reviewPicture);
}
