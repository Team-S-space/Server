package hackathon.spring.web.controller;

import hackathon.spring.apiPayload.ApiResponse;
import hackathon.spring.converter.ReviewConverter;
import hackathon.spring.domain.Review;
import hackathon.spring.service.ReviewService.ReviewCommandService;
import hackathon.spring.service.ReviewService.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import hackathon.spring.web.dto.Review.ReviewRequestDTO;
import hackathon.spring.web.dto.Review.ReviewResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @GetMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세 조회 API", description = "특정 리뷰의 상세 내용을 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "reviewId", description = "리뷰 ID"),
    })
    public ApiResponse<hackathon.spring.web.dto.ReviewResponseDTO.ReviewDetailDTO> getReviewDetail(@PathVariable Long reviewId) {

        return ApiResponse.onSuccess(reviewQueryService.getReview(reviewId));
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "리뷰 작성 API", description = "장소에 리뷰를 작성하는 API입니다.")

    public ApiResponse<ReviewResponseDTO.addReviewResultDTO> addReview(@Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
                                                                       @RequestPart("request") @Valid ReviewRequestDTO.addReviewDTO request,
                                                                       @RequestPart("reviewPicture") MultipartFile reviewPicture){
        Review newReview = reviewCommandService.addReview(request, reviewPicture);
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(newReview));
    }
}