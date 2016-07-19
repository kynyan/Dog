package dog.utils;

import dog.model.Dog;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BeforeNowValidator.class)
@Documented
public @interface BeforeNow {
    String message() default Dog.DOG_BIRTH_DATE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
