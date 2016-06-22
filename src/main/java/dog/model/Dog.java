package dog.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import dog.utils.RandomBirthDate;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static io.qala.datagen.RandomShortApi.*;

@Getter
@Setter
@ToString
public class Dog {

    public static final String DOG_NAME_SIZE_NOTE = "Should be between 1 and 100 symbols.";
    public static final int DOG_NAME_LOWER_BOUNDARY = 1;
    public static final int DOG_NAME_UPPER_BOUNDARY = 100;
    public static final String DOG_HEIGHT_WEIGHT_NOTE = "Should be strictly greater than 0";
    public static final String DOG_BIRTH_DATE = "Should be some date in the past";

    public Dog() {
    }

    public Dog(String name, LocalDate birthDate, double height, double weight, long id) {
        this.name = name;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.id = id;
    }

    @Size(min=DOG_NAME_LOWER_BOUNDARY, max = DOG_NAME_UPPER_BOUNDARY, message = DOG_NAME_SIZE_NOTE)
    private String name;

    @JsonDeserialize(using=LocalDateDeserializer.class)
    //@Past(message = DOG_BIRTH_DATE)
    private LocalDate birthDate;

    @DecimalMin(value="0", inclusive = false, message = DOG_HEIGHT_WEIGHT_NOTE)
    private double height;

    @DecimalMin(value="0", inclusive = false, message = DOG_HEIGHT_WEIGHT_NOTE)
    private double weight;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    public static Dog random() {

        Dog dog = new Dog();
        dog.setName(alphanumeric(DOG_NAME_LOWER_BOUNDARY, DOG_NAME_UPPER_BOUNDARY));
        dog.setBirthDate((new RandomBirthDate().randomBirthDate()));
        dog.setHeight((double)integer(100));
        dog.setWeight((double)integer(50));

        return dog;
    }

}
