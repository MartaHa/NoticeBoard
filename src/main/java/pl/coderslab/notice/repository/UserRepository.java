package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.notice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
