package dog.endpoint;

import dog.dao.DogDao;
import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogEndpoint {

//    private DogDao dogDao;
//
//    public DogEndpoint() {
//    }
//
//    public DogEndpoint(DogDao dogDao) {
//        this.dogDao = dogDao;
//    }
//
//    public void setDogDao(DogDao dogDao) {
//        this.dogDao = dogDao;
//    }

    @Autowired
    DogDao dogDao;

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld() {
        return "Hi!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dog", produces="application/json")
    public ResponseEntity<List<Dog>> listDogs() {
//        return ResponseEntity.ok(dogService.createRandomDogs(10));
        return ResponseEntity.ok(dogDao.createStaticDogs());
    }
}
