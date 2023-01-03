package dtalk.controller;

import dtalk.dto.user.UserSaveDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class UserControllerTest {

    @Test
    void userList() {
    }

    @Test
    void join() {
        UserSaveDTO userSaveDto = new UserSaveDTO();
        assertEquals(userSaveDto.getId(),null);
    }

    @Test
    void login() {
    }
}