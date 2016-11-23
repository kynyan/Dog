package dog.service;

import dog.model.Dog;

public class DogService3 implements IDogService {
    @Override
    public Dog createDog(Dog dog) {
        return dog;
    }
}
