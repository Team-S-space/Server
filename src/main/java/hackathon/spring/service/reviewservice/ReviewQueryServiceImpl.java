package hackathon.spring.service.reviewservice;

import hackathon.spring.apiPayload.code.status.ErrorStatus;
import hackathon.spring.apiPayload.exception.GeneralException;
import hackathon.spring.converter.ReviewDetailConverter;
import hackathon.spring.domain.Review;
import hackathon.spring.repository.ReviewRepository;
import hackathon.spring.web.dto.ReviewDetailResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final String API_KEY = "6kxaYkjhPFHK0hCs5uo13I7wOefeecQ8S/bHVRKoQkItRxGxgWyV0ParKFVRjRUlmUXBjrBNm9dRES4iw4FBOA==";

    @Override
    public ReviewDetailResponseDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.REVIEW_NOT_FOUND));

        String location = extractCityName(review.getLocation().getAddress());
        String locdate = extractCurrentDay();

        try {
            String apiResponse = callRiseSetInfoApi(location, locdate);
            Map<String, LocalDateTime> sunInfo = parseSunriseSunsetFromXml(apiResponse, locdate);

            return ReviewDetailConverter.toReviewDetailResponseDto(review, sunInfo);
        } catch (Exception e) {
            log.error("일출/일몰 API 호출 실패: {}", e.getMessage());
            throw new GeneralException(ErrorStatus.EXTERNAL_API_ERROR);
        }
    }

    private Map<String, LocalDateTime> parseSunriseSunsetFromXml(String xmlString, String date) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //XML 문자열을 Document로 파싱
        Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

        //sunrise와 sunset 노드 찾기
        NodeList sunriseList = doc.getElementsByTagName("sunrise");
        NodeList sunsetList = doc.getElementsByTagName("sunset");

        Map<String, LocalDateTime> result = new HashMap<>();

        if (sunriseList.getLength() > 0 && sunsetList.getLength() > 0) {
            String sunriseTime = sunriseList.item(0).getTextContent();
            String sunsetTime = sunsetList.item(0).getTextContent();

            //시간 문자열을 LocalDateTime으로 변환
            result.put("sunrise", parseTimeToLocalDateTime(date, sunriseTime));
            result.put("sunset", parseTimeToLocalDateTime(date, sunsetTime));
        } else {
            log.error("일출/일몰 정보를 찾을 수 없습니다.");
            throw new GeneralException(ErrorStatus.EXTERNAL_API_ERROR);
        }

        return result;
    }

    private LocalDateTime parseTimeToLocalDateTime(String date, String time) {
        // 입력된 시간에서 공백 제거
        String cleanTime = time.trim();

        if (cleanTime.length() != 4) {
            cleanTime = String.format("%04d", Integer.parseInt(cleanTime));
        }

        String dateTimeStr = date + cleanTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    private String callRiseSetInfoApi(String location, String locdate) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/RiseSetInfoService/getAreaRiseSetInfo");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode(API_KEY, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("locdate","UTF-8") + "=" + URLEncoder.encode(locdate, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("location","UTF-8") + "=" + URLEncoder.encode(location, "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            throw new IOException("API 호출 실패: " + conn.getResponseCode());
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();
    }

    private String extractCurrentDay() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private String extractCityName(String address) {
        String[] parts = address.split(" ");
        for (String part : parts) {
            if (part.endsWith("시")) {
                return part.substring(0, part.length() - 1);
            }
        }
        return "";
    }
}
