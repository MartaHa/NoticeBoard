package pl.coderslab.notice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 5, max = 25)
    private String title;

    @Size(min = 25, max = 100)
    private String summary;


    private String expirationDate;
    private LocalDateTime created;
    private LocalDateTime updated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Category> categories;

    @OneToMany(mappedBy = "notice",
            cascade = CascadeType.ALL)
    private List<Comment> comments;


    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }


}

