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

    private DogHibernateDaoImpl dogHibernateDao;

    public DogService(DogHibernateDaoImpl dogHibernateDao) {
        this.dogHibernateDao = dogHibernateDao;
    }

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

    public void addRandomDogs(List<Dog> dogList) {
        for (Dog dog:dogList) {
            dogHibernateDao.addDog(dog);
        }
    }

    public Long createDog(Dog dog) {
        return dogHibernateDao.addDog(dog);
    }

}
