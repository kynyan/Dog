package dog.model;

import dog.dao.DogHibernateDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qala.datagen.RandomElements.from;
import static io.qala.datagen.RandomShortApi.oneOf;
import static io.qala.datagen.RandomShortApi.positiveInteger;
import static io.qala.datagen.RandomValue.length;
import static io.qala.datagen.RandomDate.*;

@Test
@ContextConfiguration(locations = "classpath:app-context.xml")
public class ModelTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private DogHibernateDaoImpl dogHibernateDao;

    @Test(expectedExceptions = {javax.validation.ConstraintViolationException.class},
            expectedExceptionsMessageRegExp = ".* between 1 and 100 .*")
    public void mustThrowConstraintViolationErrorIfDogNameIsInvalid() {
        //create random dog
        Dog dogWithInvalidName = Dog.random();
        dogWithInvalidName.setName(from(length(0).alphanumeric(), length(101).alphanumeric()).sample());

        //try to add dog
        Dog addedDog = dogHibernateDao.addDog(dogWithInvalidName);
    }

    @Test(expectedExceptions = {javax.validation.ConstraintViolationException.class},
            expectedExceptionsMessageRegExp = ".* some date in the .*")
    public void mustThrowConstraintViolationErrorIfDogBirthDateIsInvalid() {
        //create random dog
        Dog dogWithInvalidBirthDate = Dog.random();
        dogWithInvalidBirthDate.setBirthDate(after(now()).localDate());

        //try to add dog
        Dog addedDog = dogHibernateDao.addDog(dogWithInvalidBirthDate);
    }

    @Test
    public void mustNotThrowConstraintViolationErrorIfDogBirthDateIsNull() {
        //create random dog
        Dog dogWithNullBirthDate = Dog.random();
        dogWithNullBirthDate.setBirthDate(null);

        //try to add dog
        Dog addedDog = dogHibernateDao.addDog(dogWithNullBirthDate);
        Assert.assertNull(addedDog.getBirthDate());
    }

    @Test(expectedExceptions = {javax.validation.ConstraintViolationException.class},
            expectedExceptionsMessageRegExp = ".* be strictly greater than .*")
    public void mustThrowConstraintViolationErrorIfDogWeightOrHeightIsInvalid() {
        //create random dog
        Dog dogWithInvalidWeightOrHeight = Dog.random();
        oneOf(() -> dogWithInvalidWeightOrHeight.setWeight((-1)*positiveInteger()),
                () -> dogWithInvalidWeightOrHeight.setHeight((-1)*positiveInteger()));

        //try to add dog
        Dog addedDog = dogHibernateDao.addDog(dogWithInvalidWeightOrHeight);
    }

}
