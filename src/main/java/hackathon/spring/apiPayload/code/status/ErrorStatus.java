package hackathon.spring.apiPayload.code.status;

import hackathon.spring.apiPayload.code.BaseErrorCode;
import hackathon.spring.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // user 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "사용자가 없습니다."),
    USER_PASSWORD_INCORRECT(HttpStatus.BAD_REQUEST, "USER4002", "비밀번호가 틀렸습니다."),
    USER_DUPLICATE(HttpStatus.BAD_REQUEST, "USER4003", "이미 존재하는 아이디입니다."),

    //리뷰 관련 오류
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,"REVIEW4001","리뷰를 찾을 수 없습니다."),
    NO_MORE_REVIEW_DATA(HttpStatus.NOT_FOUND,"REVIEW4002","더 이상 조회할 리뷰가 없습니다."),

    //API 오류
    EXTERNAL_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"API4001", "일출, 일몰 API 호출에 실패했습니다."),

    //지역 관련 오류
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND,"LOCATION4001","해당 지역은 존재하지 않는 지역입니다."),

    //일출, 일몰 값 전달 시 잘못된 값 전달
    SUN_EVENT_NOT_FOUND(HttpStatus.NOT_FOUND,"SUNEVENT4001","일출, 일몰 값을(0,1,2 중) 제대로 지정해주세요"),
    SUN_EVENT_INCORRECT(HttpStatus.BAD_REQUEST,"SUNEVENT4002","잘못된 일출, 일몰 값이 전달되지 않았습니다."),

    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}