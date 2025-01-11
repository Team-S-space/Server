package hackathon.spring.apiPayload.exception.handler;

import hackathon.spring.apiPayload.code.BaseErrorCode;
import hackathon.spring.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}