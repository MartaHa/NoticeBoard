package pl.coderslab.notice.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.notice.entity.User;
import pl.coderslab.notice.repository.RoleRepository;
import pl.coderslab.notice.repository.UserRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {


    private UserService userService;
    private UserRepository repository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* in order to test server working not repository */

    @Before
    public void setUp(){
        repository = mock(UserRepository.class);
        userService = new UserServiceImpl(repository,roleRepository,bCryptPasswordEncoder);
    }

    @Test
    public void findByUserName() {

    //given

    User testUser1 = new User();
        testUser1.setFirstName("John");
        testUser1.setUsername("John1");

    User testUser2 = new User();
        testUser2.setFirstName("Dave");
        testUser2.setUsername("Dave2");

    when(repository.findByUsername("John1")).thenReturn(testUser1);
    when(repository.findByUsername("Dave2")).thenReturn(testUser2);


    //when
    User user3 = userService.findByUserName("John1");
    User user4 = userService.findByUserName("Dave2");

    //then
    assertEquals(user3.getFirstName(), "John");
    assertEquals(user4.getFirstName(), "Dave");
}


    @Test
    public void saveUser() {
    }
}