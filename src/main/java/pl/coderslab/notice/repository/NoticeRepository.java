package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.notice.entity.Notice;

import java.util.List;

public interface NoticeRepository extends JpaRepository <Notice, Long> {

    @Query(value = "SELECT * comments FROM category WHERE category_name= ?1", nativeQuery = true)
    long findAllCommentsByNotice(String category);

    @Query(value = "SELECT * FROM notice WHERE user_id = ?1", nativeQuery = true)
    List<Notice> findNoticesbyUserId(long id);
}
