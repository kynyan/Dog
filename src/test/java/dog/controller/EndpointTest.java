package dog.controller;

import com.jayway.restassured.response.Response;
import dog.dao.DogInMemoryDao;
import dog.model.Dog;
import dog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@org.testng.annotations.Test
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class EndpointTest extends AbstractTestNGSpringContextTests {

    @Autowired
    DogService dogService;

//    public EndpointTest() {}
//    public EndpointTest(DogService dogService) {
//        this.dogService = dogService;
//    }

    @org.testng.annotations.Test
    public void mustReturnCreatedListOfDogs() {
        //create static collection of Dogs
//        List<Dog> dogs = dogInMemoryDao.createStaticDogs();

        //create random collection of Dogs
        List<Dog> dogs = dogService.createRandomDogs(2);

        Response response = given().body(dogs.get(0)).post("/dog").
                then().extract().response();

        Response response1 = given().get("/dog").then().extract().response();
        List<Dog> fetchedDogs = Arrays.asList(response1.getBody().as(Dog[].class));
        assertReflectionEquals(dogs,fetchedDogs);
    }
}
