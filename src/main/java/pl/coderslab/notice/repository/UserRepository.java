package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.notice.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
