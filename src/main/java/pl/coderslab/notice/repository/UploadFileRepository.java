package pl.coderslab.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.notice.entity.File;

public interface UploadFileRepository extends JpaRepository<File, Long> {
}
