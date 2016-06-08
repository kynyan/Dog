package dog;

import com.jayway.restassured.response.Response;
import dog.model.Dog;
import dog.service.DogService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.jayway.restassured.RestAssured.when;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class Test {

    @Autowired
    DogService dogService;

    @org.junit.Test
    public void mustReturnCreatedListOfDogs() {
        //create static collection of Dogs
        List<Dog> dogs = dogService.createStaticDogs();

        Response response = when().get("/dog").
                then().extract().response();
        assertReflectionEquals(dogs, response);

    }
}
