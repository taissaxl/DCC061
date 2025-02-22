package eng.software.reveste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eng.software.reveste.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    eng.software.reveste.model.User findByUsername(String username);
}
