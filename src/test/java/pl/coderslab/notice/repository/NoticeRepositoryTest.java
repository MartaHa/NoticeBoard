package pl.coderslab.notice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.notice.entity.Notice;
import pl.coderslab.notice.entity.User;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoticeRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllNoticesbyUserId() {

        //given

     User user1 = new User();

        user1.setUsername("username");
        user1.setDateOfBirth("1985-01-01");
        user1.setPassword("password");
        user1.setEmail("anna@email.com");
        user1.setEnabled(1);
        user1.setFirstName("Anna");
        user1.setLastName("Wesołowska");
        user1.setPhoneNumber("607607677");

        entityManager.persist(user1);

        List<Notice> notices = new ArrayList<>();

        Notice notice1 = new Notice();
        notice1.setTitle("title1");
        notice1.setSummary("blablablablllefkjrkereejrfrefe1");
        notice1.setUser(user1);
        entityManager.persist(notice1);

        Notice notice2 = new Notice();

        notice2.setTitle("title2");
        notice2.setSummary("blablblablabllllllllllllllllllllllla2");
        notice2.setUser(user1);

        entityManager.persist(notice2);

        notices.add(notice1);
        notices.add(notice2);


        user1.setNotices(notices);

        entityManager.persist(user1);


        //when

        List<Notice> notices2 = noticeRepository.findAllNoticesbyUserId(user1.getId());

        //then

        assertEquals(2, notices2.size());

    }

    @Test
    public void findAllCommentsByNotice() {
    }

    @Test
    public void findAllNoticesbyUserId1() {
    }

    @Test
    public void findAllActiveNotices() {
    }
}