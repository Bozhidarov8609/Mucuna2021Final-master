package ex.repository;

import ex.model.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<Vet,Long> {
    Optional<Vet> findByVetName(String vetName);
@Query("SELECT v FROM Vet AS v WHERE v.user.id=:id")
    Vet findByUserEntityId(Long id);


}
