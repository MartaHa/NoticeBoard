package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository <Notice, Long> {

    @Query(value = "SELECT * comments FROM category WHERE category_name= ?1", nativeQuery = true)
    long findAllCommentsByNotice(String category);
}
