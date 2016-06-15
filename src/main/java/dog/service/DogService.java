package dog.service;


import dog.dao.DogHibernateDaoImpl;
import dog.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DogService {

    @Autowired
    DogHibernateDaoImpl dogHibernateDao;

    public List<Dog> createRandomDogs(final Integer amt) {
        List<Dog> result = new ArrayList<Dog>();
        for (int i = 0; i < amt; i++) {
            Dog createdDog = Dog.random();
            result.add(createdDog);
        }
        return result;
    }

    public List<Dog> getAllDogs() {
        return dogHibernateDao.getAllDogs();
    }

}
