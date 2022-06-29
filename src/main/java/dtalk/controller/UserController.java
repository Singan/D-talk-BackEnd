package dtalk.controller;

import dtalk.dto.user.*;
import dtalk.security.token.JwtTokenProvider;
import dtalk.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@Tag(name = "UserController")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/list")
    @Operation(description = "회원리스트")
    public List<UserResponseDTO> userList(){

        return UserResponseDTO.createUserResDto(userService.userList());
    }
    @PostMapping
    @Operation(description = "회원가입")
    public Long save(@Parameter @RequestBody @Valid UserSaveDTO userSaveDto){
        System.out.println("userController");
        userSaveDto.setPw(passwordEncoder.encode(userSaveDto.getPw()));
        return userService.save(userSaveDto);
    }


    @PostMapping("/login")
    @Operation(description = "로그인")
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
    @Operation(description = "유저검색")
    public UserFindDTO findUser(@RequestParam(name = "userId") String userId){
        System.out.println("파인드유저"+userId);
        return UserFindDTO.createUserFindDto(userService.findByUserId(userId));
    }
}
