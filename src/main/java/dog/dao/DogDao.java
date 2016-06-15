package dog.dao;

import dog.model.Dog;

import java.util.List;

public interface DogDao {
    public void addDog(Dog dog);
    public List<Dog> getAllDogs();
    public void deleteDog(Integer dogId);
}
