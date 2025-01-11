package hackathon.spring.validation.validator;

import hackathon.spring.validation.annotation.ValidLatLng;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LatLngValidator implements ConstraintValidator<ValidLatLng, String> {
    private static final double MIN_LAT = 37.0;
    private static final double MAX_LAT = 38.0;
    private static final double MIN_LNG = 126.0;
    private static final double MAX_LNG = 127.0;

    @Override
    public void initialize(ValidLatLng constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return false;

        try {
            double number = Double.parseDouble(value);
            return (number >= MIN_LAT && number <= MAX_LAT) || (number >= MIN_LNG && number <= MAX_LNG);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}