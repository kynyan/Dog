package dog.dao;

import dog.model.Dog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DogInMemoryDao {
    public List<Dog> createStaticDogs() {
        List<Dog> result = new ArrayList<Dog>();
        LocalDate birthDate1 = LocalDate.of(1994, 4, 5);
        LocalDate birthDate2 = LocalDate.of(2005, 1, 15);
        Dog createdDog1 = new Dog("afsdflkj", birthDate1, 10, 20, 1);
        Dog createdDog2 = new Dog("dfgfdh", birthDate2, 30, 42, 2);
        result.add(createdDog1);
        result.add(createdDog2);
        return result;
    }
}
