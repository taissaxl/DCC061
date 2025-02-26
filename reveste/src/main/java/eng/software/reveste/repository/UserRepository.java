package eng.software.reveste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eng.software.reveste.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Retorna um Optional<User>
}