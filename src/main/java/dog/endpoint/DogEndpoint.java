package dog.endpoint;

import dog.dao.DogInMemoryDao;
import dog.model.Dog;
import dog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogEndpoint {

    @Autowired
    DogService dogService;

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
}
