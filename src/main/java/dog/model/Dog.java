package dog.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Dog {

    public static final String DOG_NAME_SIZE_NOTE = "Should be between 1 and 100 symbols.";
    public static final int DOG_NAME_LOWER_BOUNDARY = 1;
    public static final int DOG_NAME_UPPER_BOUNDARY = 100;
    public static final String DOG_HEIGHT_WEIGHT_NOTE = "Should be strictly greater than 0";
    public static final String DOG_BIRTH_DATE = "Should be some date in the past";

    @Size(min=DOG_NAME_LOWER_BOUNDARY, max = DOG_NAME_UPPER_BOUNDARY, message = DOG_NAME_SIZE_NOTE)
    private String name;

    @Past(message = DOG_BIRTH_DATE)
    private LocalDate birthDate;

    @DecimalMin(value="0", inclusive = false, message = DOG_HEIGHT_WEIGHT_NOTE)
    private Double height;

    @DecimalMin(value="0", inclusive = false, message = DOG_HEIGHT_WEIGHT_NOTE)
    private Double weight;

    @Id
    private Long id;
}
