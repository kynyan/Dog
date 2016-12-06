package dog.dao;

import dog.model.Dog;
import org.hibernate.Session;

import java.util.List;

public class DogDaoSessionImpl implements DogDao {
    private Session currentSession;

    public DogDaoSessionImpl(Session session) {
        this.currentSession = session;
    }

    @Override
    public Dog addDog(Dog dog) {
        this.currentSession.save(dog);
        return dog;
    }

    @Override
    public Dog updateDog(Dog dog) {
        return (Dog) this.currentSession.merge(dog);
    }

    @Override
    public List<Dog> getAllDogs() {
        return this.currentSession.createQuery("from Dog").list();
    }

    @Override
    public Dog getDogById(long id) {
        return (Dog) this.currentSession.get(Dog.class, id);
    }

    @Override
    public void deleteDog(long dogId) {
        Dog dog = (Dog) this.currentSession.load(
                Dog.class, dogId);
        if (null != dog) {
            this.currentSession.delete(dog);
        }
    }
}
