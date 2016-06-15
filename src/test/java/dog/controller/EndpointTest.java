package dog.controller;

import com.jayway.restassured.response.Response;
import dog.dao.DogInMemoryDao;
import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.when;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@org.testng.annotations.Test
@ContextConfiguration(locations = "classpath:test-context.xml")
//@TestPropertySource(properties = {"port: 8080"} )
public class EndpointTest extends AbstractTestNGSpringContextTests {

    @Autowired
    DogInMemoryDao dogInMemoryDao;

    @org.testng.annotations.Test
    public void mustReturnCreatedListOfDogs() {
        //create static collection of Dogs
        List<Dog> dogs = dogInMemoryDao.createStaticDogs();

        Response response = when().get("/dog").
                then().extract().response();
        List<Dog> fetchedDogs = Arrays.asList(response.getBody().as(Dog[].class));
        assertReflectionEquals(dogs,fetchedDogs);
    }
}
