package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.notice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}


