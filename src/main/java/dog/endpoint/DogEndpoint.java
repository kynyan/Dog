package dog.endpoint;

import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dog.service.DogService;

import java.util.List;

@RestController
public class DogEndpoint {

    @Autowired
    private DogService dogService;

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld() {
        return "Hi!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dog/{id}")
    public Dog getDog(@PathVariable Long id) {
        return dogService.findOneById(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dog", produces="application/json")
    public ResponseEntity<List<Dog>> listDogs() {
//        return ResponseEntity.ok(dogService.createRandomDogs(10));
        return ResponseEntity.ok(dogService.createStaticDogs());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/dog")
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.createDog(dog);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/dog/{id}")
    public ResponseEntity<?> deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/dog/{id}")
    public Dog updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        return dogService.updateDog(id, dog);
    }

}
