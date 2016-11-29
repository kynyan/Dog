package dog.endpoint;

import dog.model.Dog;
import dog.service.DogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@RestController
public class DogEndpoint {

    private DogService dogService;
    private MessageSource messageSource;

    public DogEndpoint(DogService dogService, MessageSource messageSource) {
        this.dogService = dogService;
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld(@RequestParam Locale locale) {
        return messageSource.getMessage("message", null, "Default", locale);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dogs", produces="application/json")
    public ResponseEntity<List<Dog>> listDogs() {
        return ResponseEntity.ok(dogService.getAllDogs());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dog/{id}", produces="application/json")
    public ResponseEntity<Dog> getDogById(@PathVariable long id) {
        return ResponseEntity.ok(dogService.getDogById(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/dog")
    public ResponseEntity createDog(@RequestBody Dog dog) {
        return ResponseEntity.ok(dogService.createDog(dog));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/dog/{id}")
    public ResponseEntity updateDog(@PathVariable long id, @RequestBody Dog dog) {
        return ResponseEntity.ok(dogService.updateDog(dog));
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/dog/{id}")
    public ResponseEntity deleteDog(@PathVariable long id) {
        dogService.deleteDog(id);
        return ResponseEntity.noContent().build();
    }

    public void init() {
        List<Dog> dogList = dogService.createRandomDogs(2);
        dogService.addRandomDogs(dogList);
    }
}
