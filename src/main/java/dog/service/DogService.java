package dog.service;


import dog.dao.DogRepository;
import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    @Transactional
    public Dog createDog(final Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog findOneById(final Long id) {
        Dog one = dogRepository.findOne(id);
        return one;
    }

    public void deleteDog(final Long id) {
        dogRepository.delete(id);
    }

//    @Transactional
//    public Dog updateDog(final Long id, final Dog dog) {
//        dog.setId(id);
//        return dogRepository.save(dog);
//    }
}
