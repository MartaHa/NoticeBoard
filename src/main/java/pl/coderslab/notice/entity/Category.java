package pl.coderslab.notice.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany
    private  List <Notice> notices;

}
