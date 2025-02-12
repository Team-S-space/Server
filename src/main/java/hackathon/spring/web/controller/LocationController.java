package hackathon.spring.web.controller;

import hackathon.spring.apiPayload.ApiResponse;
import hackathon.spring.apiPayload.code.status.ErrorStatus;
import hackathon.spring.apiPayload.exception.handler.TempHandler;
import hackathon.spring.domain.enums.Sun;
import hackathon.spring.service.LocationService.LocationService;
import hackathon.spring.web.dto.LocationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/home")
@RequiredArgsConstructor
@RestController
public class LocationController {
    private final LocationService locationService;

    @GetMapping("")
    @Operation(summary = "일출, 일몰 명소 조회 API")
    public ApiResponse<List<LocationResponseDTO.getLocationDTO>> getLocationList(@RequestParam Integer sunEvent) {
        Sun sun;
        if(sunEvent == 0)
            sun = Sun.SUNRISE;
        else if (sunEvent == 1) {
            sun = Sun.SUNSET;
        } else {
            throw new TempHandler(ErrorStatus.SUN_EVENT_INCORRECT);
        }

        return ApiResponse.onSuccess(locationService.getLocationList(sun));
    }
}
