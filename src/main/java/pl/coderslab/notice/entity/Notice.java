package pl.coderslab.notice.entity;


import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private String expirationDate;

    @ManyToOne
    private User user;


    @OneToMany
    private List<Comment> comment = new ArrayList<>();


//
//    @ManyToMany
//    private List<Category> category = new ArrayList<>();


}

