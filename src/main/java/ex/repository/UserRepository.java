package ex.repository;

import ex.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsernameAndEmail(String username, String email);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByUsername(String username);
@Query ("SELECT U FROM UserEntity AS U WHERE U.role.id = 2 Or U.role.id = 3")
  List<UserEntity> findAllWithoutAdminRole();

@Query("SELECT U FROM UserEntity AS U WHERE U.id= :id")
UserEntity findByUserId(Long id);
}
