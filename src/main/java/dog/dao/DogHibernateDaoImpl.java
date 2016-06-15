package dog.dao;

import dog.model.Dog;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogHibernateDaoImpl implements DogDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addDog(Dog dog) {
        this.sessionFactory.getCurrentSession().save(dog);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dog> getAllDogs() {
        return this.sessionFactory.getCurrentSession().createQuery("from Dog").list();
    }
    @Override
    public void deleteDog(Integer dogId) {
        Dog dog = (Dog) sessionFactory.getCurrentSession().load(
                Dog.class, dogId);
        if (null != dog) {
            this.sessionFactory.getCurrentSession().delete(dog);
        }
    }
}
