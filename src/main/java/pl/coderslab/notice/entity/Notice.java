package pl.coderslab.notice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private String expirationDate;
//    private LocalDateTime created;

    @ManyToOne
    private User user;

//    @PrePersist
//    protected void onCreate() {
//        created = LocalDateTime.now();
//    }

}

