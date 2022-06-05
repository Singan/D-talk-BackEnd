package dtalk.controller;

import dtalk.dto.user.*;
import dtalk.security.token.JwtTokenProvider;
import dtalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/list")
    public List<UserResponseDTO> userList(){

        return UserResponseDTO.createUserResDto(userService.userList());
    }
    @PostMapping()
    public Long save(@RequestBody @Valid UserSaveDTO userSaveDto){
        System.out.println("userController");
        userSaveDto.setPw(passwordEncoder.encode(userSaveDto.getPw()));
        return userService.save(userSaveDto);
    }


    @PostMapping("/login")
    public String login(@RequestBody @Valid UserLoginDTO user) throws IOException {
        UserDetailDTO u =  new UserDetailDTO(userService.findByUserId(user.getId()));
        if (!passwordEncoder.matches( user.getPw(),u.getPassword())) { // 왼쪽이 인코딩 되지 않은 값
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String jwt = jwtTokenProvider.createToken(u.getUsername(), u.getRoles());
        System.out.println("jwt ====" + jwt);
        return jwt;
    }
    @GetMapping
    public UserFindDTO findUser(@RequestBody String userId){
        return UserFindDTO.createUserFindDto(userService.findByUserId(userId));
    }
}
