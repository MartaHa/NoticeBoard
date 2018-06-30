package pl.coderslab.notice.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @ManyToMany
//    private List<Notice> notices;
}
