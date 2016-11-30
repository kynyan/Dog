package dog.dao;

import dog.model.Dog;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogHibernateDaoImpl implements DogDao {

    private SessionFactory sessionFactory;

    public DogHibernateDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Dog addDog(Dog dog) {
        this.sessionFactory.getCurrentSession().save(dog);
        return dog;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dog> getAllDogs() {
        return this.sessionFactory.getCurrentSession().createQuery("from Dog").list();
    }

    @Override
    public Dog getDogById(long id) {
        return (Dog) this.sessionFactory.getCurrentSession().get(Dog.class, id);
    }

    @Override
    public Dog updateDog(Dog dog) {
        Dog dogUpdated = (Dog) this.sessionFactory.getCurrentSession().merge(dog);
        return dogUpdated;
    }

    @Override
    public void deleteDog(long dogId) {
        Dog dog = (Dog) sessionFactory.getCurrentSession().load(
                Dog.class, dogId);
        if (null != dog) {
            this.sessionFactory.getCurrentSession().delete(dog);
        }
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
