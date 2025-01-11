package hackathon.spring.web.controller;

import hackathon.spring.apiPayload.ApiResponse;
import hackathon.spring.service.reviewservice.ReviewQueryService;
import hackathon.spring.web.dto.ReviewDetailResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@RestController
public class ReviewDetailController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세 조회 API", description = "특정 리뷰의 상세 내용을 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "reviewId", description = "리뷰 ID"),
    })
    public ApiResponse<ReviewDetailResponseDto> getReviewDetail(@PathVariable Long reviewId) {

        return ApiResponse.onSuccess(reviewQueryService.getReview(reviewId));
    }
}
