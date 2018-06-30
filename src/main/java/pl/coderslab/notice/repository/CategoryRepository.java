package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.notice.entity.Category;

public interface CategoryRepository extends JpaRepository <Category, Long> {

}
