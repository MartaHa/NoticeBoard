package pl.coderslab.notice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.notice.entity.User;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class NoticeRepositoryTest {

    @Test
    public void findAllNoticesbyUserId() {

        //given

    }
}