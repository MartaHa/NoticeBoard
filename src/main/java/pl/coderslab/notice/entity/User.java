package pl.coderslab.notice.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    private int enabled;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;


    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notice> notices = new ArrayList<>();;


}
