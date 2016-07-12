package dog.controller;

import com.jayway.restassured.http.ContentType;
import dog.model.Dog;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static com.jayway.restassured.RestAssured.given;
import static io.qala.datagen.RandomShortApi.english;
import static org.testng.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@org.testng.annotations.Test
@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class ControllerTest extends AbstractTestNGSpringContextTests {

    @org.testng.annotations.Test
    public void mustReturnCreatedDog() {
        //create random dog
        Dog originalDog = Dog.random();

        //post created dog
        Dog fetchedDog = postDog(originalDog);

        originalDog.setId(fetchedDog.getId());

        assertReflectionEquals(originalDog, fetchedDog);
    }

    @org.testng.annotations.Test
    public void mustUpdateDog() {
        //post random dog
        Dog originalDog = postDog(Dog.random());

        //update name of the dog using patch
        originalDog.setName(english(5));
        Dog updatedDog = given().contentType(ContentType.JSON).
                body(originalDog).put("/dog/{id}", originalDog.getId()).peek().
                then().extract().response().body().as(Dog.class);

        assertReflectionEquals(originalDog, updatedDog);
    }

    @org.testng.annotations.Test
    public void mustDeleteDog() {
        //get all dogs
        Dog[] dogs = given().get("/dogs").
                then().extract().response().body().as(Dog[].class);

        //delete 1st dog of the array
        given().contentType(ContentType.JSON).expect().statusCode(HttpStatus.NO_CONTENT.value()).
                when().delete("/dog/{id}", dogs[0].getId());
        Dog[] dogsAfterDelete = given().get("/dogs").
                then().extract().response().body().as(Dog[].class);

        assertEquals(dogs.length - 1, dogsAfterDelete.length);
    }

    private Dog postDog(Dog dogToCreate) {
        return given().contentType(ContentType.JSON).body(dogToCreate).post("/dog").
                then().extract().response().body().as(Dog.class);
    }

}
