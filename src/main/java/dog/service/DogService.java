package dog.service;


import dog.dao.DogRepository;
import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    @Transactional
    public Dog createDog(final Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog findOneById(final Long id) {
        return dogRepository.findOne(id);
    }

    public List<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    public void deleteDog(final Long id) {
        dogRepository.delete(id);
    }

    @Transactional
    public Dog updateDog(final Long id, final Dog dog) {
        dog.setId(id);
        return dogRepository.save(dog);
    }

    public List<Dog> createRandomDogs(final Integer amt) {
        List<Dog> result = new ArrayList<Dog>();
        for (int i = 0; i < amt; i++) {
            Dog createdDog = Dog.random();
            result.add(createdDog);
        }
        return result;
    }

    public List<Dog> createStaticDogs() {
        List<Dog> result = new ArrayList<Dog>();
        LocalDate birthDate1 = LocalDate.of(1994, 4, 5);
        LocalDate birthDate2 = LocalDate.of(2005, 1, 15);
        Dog createdDog1 = new Dog("afsdflkj", birthDate1, 10, 20, 1);
        Dog createdDog2 = new Dog("dfgfdh", birthDate2, 30, 42, 2);
        result.add(createdDog1);
        result.add(createdDog2);
        return result;
    }
}
