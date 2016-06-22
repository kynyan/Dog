package dog.endpoint;

import dog.dao.DogInMemoryDao;
import dog.model.Dog;
import dog.service.DogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogEndpoint {

    DogService dogService;

    public DogEndpoint(DogService dogService) {
        this.dogService = dogService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld() {
        return "Hi!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dog", produces="application/json")
    public ResponseEntity<List<Dog>> listDogs() {
//        return ResponseEntity.ok(dogService.createRandomDogs(10));
//        return ResponseEntity.ok(dogInMemoryDao.createStaticDogs());
        return ResponseEntity.ok(dogService.getAllDogs());
    }

    public void init() {
        List<Dog> dogList = dogService.createRandomDogs(2);
        dogService.addRandomDogs(dogList);
    }
}
