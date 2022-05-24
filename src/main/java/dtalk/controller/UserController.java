package dtalk.controller;

import dtalk.domain.User;
import dtalk.dto.UserResponseDto;
import dtalk.dto.UserSaveDto;
import dtalk.repository.UserRepository;
import dtalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService UserService;

    @GetMapping("/user/list")
    public List<UserResponseDto> userList(){

        return UserService.userList();
    }
    @PostMapping("/user")
    public Long join(UserSaveDto userSaveDto){

        return UserService.save(userSaveDto);
    }
}
