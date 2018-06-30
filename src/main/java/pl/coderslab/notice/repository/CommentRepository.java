package pl.coderslab.notice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.notice.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
