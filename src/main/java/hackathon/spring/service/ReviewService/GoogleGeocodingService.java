package hackathon.spring.service.ReviewService;

import org.json.JSONArray;
import org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GoogleGeocodingService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;


    public String getFormattedAddress(double latitude, double longitude) {
        // API 요청 URL 구성
        String url = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/geocode/json")
                .queryParam("latlng", latitude + "," + longitude)
                .queryParam("key", apiKey)
                .queryParam("language", "ko")
                .toUriString();

        try {
            String response = restTemplate.getForObject(url, String.class);
            if (response == null) {
                return "주소를 찾을 수 없습니다.";
            }

            JSONObject jsonResponse = new JSONObject(response);
            JSONArray results = jsonResponse.getJSONArray("results");
            if (results.length() == 0) {
                return "주소를 찾을 수 없습니다.";
            }

            String formattedAddress = results.getJSONObject(0).getString("formatted_address");
            return formattedAddress;
        } catch (Exception e) {
            return "주소를 찾는 중 오류가 발생했습니다.";
        }
    }
}