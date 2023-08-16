package finalmelontoken.sharestudying.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import finalmelontoken.sharestudying.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    User getUserByName(String name);
}