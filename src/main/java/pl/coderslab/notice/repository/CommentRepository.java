package pl.coderslab.notice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.notice.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //find comments by Notice id

    @Query(value = "SELECT *  FROM comment WHERE notice_id= ?1", nativeQuery = true)
    List<Comment>  findAllCommentsByNotice(long id);

}
