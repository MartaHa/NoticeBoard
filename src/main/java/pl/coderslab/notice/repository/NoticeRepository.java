package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.notice.entity.Notice;

import javax.persistence.TemporalType;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {




    //find notices by Id
    @Query(value = "SELECT * FROM notice WHERE user_id = ?1", nativeQuery = true)
    List<Notice> findAllNoticesbyUserId(long id);


    //find active notices
    @Query(value = "SELECT * FROM notice WHERE expiration_date < DATE ", nativeQuery = true)
    List<Notice> findAllActiveNotices();


}
