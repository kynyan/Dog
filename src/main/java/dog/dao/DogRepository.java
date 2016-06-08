package dog.dao;


import dog.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DogRepository extends JpaRepository<Dog, Long> {

}
