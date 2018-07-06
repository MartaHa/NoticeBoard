package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository <Notice, Long> {
}