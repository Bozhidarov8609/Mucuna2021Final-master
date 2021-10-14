package ex.repository;

import ex.model.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealerRepository extends JpaRepository<Dealer,Long> {
    Optional<Dealer> findByCompanyName(String companyName);
@Query("SELECT D from Dealer as D WHERE D.userEntity.id=:id")
    Dealer findByUserEntityId(Long id);
}
