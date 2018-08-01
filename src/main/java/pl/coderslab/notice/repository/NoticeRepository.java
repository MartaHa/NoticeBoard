package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.notice.entity.Notice;

import javax.persistence.TemporalType;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {


    //find comments by Notice
    @Query(value = "SELECT * comment_text FROM comment WHERE notice_id= ?1", nativeQuery = true)
    long findAllCommentsByNotice(long id);


    //find notices by Id
    @Query(value = "SELECT * FROM notice WHERE user_id = ?1", nativeQuery = true)
    List<Notice> findAllNoticesbyUserId(long id);


    //find active notices
    @Query(value = "SELECT * FROM notice WHERE expiration_date < DATE ", nativeQuery = true)
    List<Notice> findAllActiveNotices();


}
