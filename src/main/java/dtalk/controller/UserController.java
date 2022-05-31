package dtalk.controller;

import dtalk.domain.User;
import dtalk.dto.UserDetailDTO;
import dtalk.dto.UserLoginDTO;
import dtalk.dto.UserResponseDto;
import dtalk.dto.UserSaveDto;
import dtalk.repository.UserRepository;
import dtalk.security.token.JwtTokenProvider;
import dtalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/user/list")
    public List<UserResponseDto> userList(){
        System.out.println("userList");
        return UserResponseDto.createUserResDto(userService.userList());
    }
    @PostMapping("/user")
    public Long join(UserSaveDto userSaveDto){
        System.out.println("userController");
        userSaveDto.setPw(passwordEncoder.encode(userSaveDto.getPw()));
        return userService.save(userSaveDto);
    }


    @PostMapping("/user/login")
    public String login(UserLoginDTO user) throws IOException {
        UserDetailDTO u =  new UserDetailDTO(userService.findByUserId(user.getId()));
        if (!passwordEncoder.matches( user.getPw(),u.getPassword())) { // 왼쪽이 인코딩 되지 않은 값
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String jwt = jwtTokenProvider.createToken(u.getUsername(), u.getRoles());
        System.out.println("jwt ====" + jwt);
        return jwt;
    }
}
