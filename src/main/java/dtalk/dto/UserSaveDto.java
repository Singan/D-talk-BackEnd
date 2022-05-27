package dtalk.dto;

import dtalk.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collections;

@Data
@AllArgsConstructor
public class UserSaveDto {
    @NotBlank(message = "아이디는 빈칸 일 수 없습니다.")
    private String id;
    @NotBlank(message = "비밀번호는 빈칸 일 수 없습니다.")
    private String pw;
    @NotBlank(message = "닉네임은 빈칸 일 수 없습니다.")
    private String nickname;

    private String profileImg;

    public User createUser() {
        User user = new User();
        user.setId(id);
        user.setPw(pw);
        user.setNickname(nickname);
        user.setProfileImg(profileImg);
        user.setBgmStatus(100);
        user.setRole("ROLE_USER");
        return user;
    }
}
