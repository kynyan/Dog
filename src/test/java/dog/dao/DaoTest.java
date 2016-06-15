package dog.dao;

import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@org.testng.annotations.Test
@ContextConfiguration(locations = "classpath:test-context.xml")
public class DaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    DogInMemoryDao dogInMemoryDao;

    @org.testng.annotations.Test
    public void mustReturnCreatedListOfDogs() {

        //create static collection of Dogs using DAO
        List<Dog> dogsFromDao = dogInMemoryDao.createStaticDogs();

        //create same collection without DAO
        List<Dog> dogs = new ArrayList<Dog>();
        LocalDate birthDate1 = LocalDate.of(1994, 4, 5);
        LocalDate birthDate2 = LocalDate.of(2005, 1, 15);
        Dog createdDog1 = new Dog("afsdflkj", birthDate1, 10, 20, 1);
        Dog createdDog2 = new Dog("dfgfdh", birthDate2, 30, 42, 2);
        dogs.add(createdDog1);
        dogs.add(createdDog2);

        assertReflectionEquals(dogs, dogsFromDao);
    }
}
