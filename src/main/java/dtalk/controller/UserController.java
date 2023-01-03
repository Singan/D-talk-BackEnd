package dtalk.controller;

import dtalk.domain.User;
import dtalk.dto.user.*;
import dtalk.security.token.JwtTokenProvider;
import dtalk.service.QuizService;
import dtalk.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@Tag(name = "UserController")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final QuizService quizService;
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
        User findUser = userService.findByUserId(userId);
        Long count = quizService.listCount(findUser);

        return UserFindDTO.createUserFindDto(findUser,count);
    }
    @GetMapping("/profile")
    @Operation(description = "유저 프로필")
    public UserUpdateDTO profileUser(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return UserUpdateDTO.createUserUpdateDTO(me);
    }
    @PutMapping(value = "/profile", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(description = "유저 프로필(일단 이미지 업로드하면 이미지 오리지널 네임 리턴하게 해놓았슴)")
    public String UpdateUser(@RequestPart UserUpdateDTO userUpdateDTO,@RequestPart MultipartFile imgFile){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        System.out.println(me.getIdx());
        if(me.getNickname()!=null)
            me.setNickname(userUpdateDTO.getNickname());
        if(me.getProfileImg()!=null)
            me.setProfileImg(userUpdateDTO.getProfileImg());
        if(me.getBgmStatus()!=null)
            me.setBgmStatus(userUpdateDTO.getBgmStatus());
        userService.UpdateUser(me);

        return imgFile.getOriginalFilename();
    }
}
