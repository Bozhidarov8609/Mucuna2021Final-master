package ex.repository;

import ex.model.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog,Long> {

  @Query("SELECT d from Dog AS d where d.approved=false")
   List<Dog> findByNotApprevedDogs();
    @Query("SELECT d from Dog AS d where d.approved=true")
    List<Dog> findAllApprovedDogs();

    Dog findByName(String name);
}
