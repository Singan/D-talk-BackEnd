package dtalk.repository;

import dtalk.domain.CUTime;
import dtalk.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    @Transactional
    @Rollback(value = false)
    void save() {
        User user = new User();
        user.setId("아이디");
        user.setPw("asdplasd,pasdasd");
        user.setNickname("이미르");
        user.setProfileImg("이미지");

        user.setCuTime(new CUTime(LocalDateTime.now()));
        Long l = userRepository.save(user);

        Assert.assertEquals(l,user.getIdx());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findAll() {
    }
}