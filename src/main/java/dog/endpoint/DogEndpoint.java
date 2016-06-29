package dog.endpoint;

import dog.dao.DogInMemoryDao;
import dog.model.Dog;
import dog.service.DogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogEndpoint {

    private DogService dogService;

    public DogEndpoint(DogService dogService) {
        this.dogService = dogService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld() {
        return "Hi!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dog", produces="application/json")
    public ResponseEntity<List<Dog>> listDogs() {
//        return ResponseEntity.ok(dogInMemoryDao.createStaticDogs());
        return ResponseEntity.ok(dogService.getAllDogs());
    }

    @RequestMapping(method = RequestMethod.POST, headers = {"content-type=application/json"}, consumes="application/json", path = "/dog")
    public void createDog(@RequestBody Dog dog) {
        ResponseEntity.ok(dogService.createDog(dog));
    }

    public void init() {
        List<Dog> dogList = dogService.createRandomDogs(2);
        dogService.addRandomDogs(dogList);
    }
}
