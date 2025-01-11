package hackathon.spring.validation.annotation;

import hackathon.spring.validation.validator.LatLngValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LatLngValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLatLng {
    String message() default "유효하지 않은 위도, 경도입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}