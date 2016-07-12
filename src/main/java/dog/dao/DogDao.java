package dog.dao;

import dog.model.Dog;

import java.util.List;

public interface DogDao {
    public Dog addDog(Dog dog);
    public Dog updateDog(Dog dog);
    public List<Dog> getAllDogs();
    public Dog getDogById(long id);
    public void deleteDog(long dogId);
}
