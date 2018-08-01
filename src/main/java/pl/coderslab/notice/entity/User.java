package pl.coderslab.notice.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 20)
    private String username;

    @NotBlank
    @Size(min= 5, max =20)
    private String password;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @NotBlank
    @Size(min = 2, max=10)
    private String firstName;

    @NotBlank
    @Size(min =2, max=10)
    private String lastName;


    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Past
    private String dateOfBirth;


    @OneToMany
    private List<Notice> notices = new ArrayList<>();


    //deleteuser -- removing roles
    @PreRemove
    public void deleteRoles() {
        this.getRoles().clear();
    }
}
