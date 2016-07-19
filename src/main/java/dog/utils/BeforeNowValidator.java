package dog.utils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BeforeNowValidator implements ConstraintValidator<BeforeNow, LocalDate> {
    public final void initialize(final BeforeNow annotation) {}

    public final boolean isValid(final LocalDate value,
                                 final ConstraintValidatorContext context) {
        return value.isBefore(LocalDate.now());
    }
}
