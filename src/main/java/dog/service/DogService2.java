package dog.service;

import dog.dao.DogHibernateDaoImpl;
import dog.model.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DogService2 implements IDogService {
    private DogHibernateDaoImpl dogHibernateDao;

    public DogService2(DogHibernateDaoImpl dogHibernateDao) {
        this.dogHibernateDao = dogHibernateDao;
    }
    @Override
    public Dog createDog(Dog dog) {
        Dog newDog = dogHibernateDao.addDog(dog);
        log.info("new dog was created");
        return newDog;
    }
}
