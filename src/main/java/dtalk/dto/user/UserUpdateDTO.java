package dtalk.dto.user;

import dtalk.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
public class UserUpdateDTO {
    private String pw;
    private String nickname;
    private String profileImg;
    private Integer bgmStatus;
    public User createUser() {
        User user = new User();
        user.setPw(pw);
        user.setNickname(nickname);
        user.setProfileImg(profileImg);
        user.setBgmStatus(bgmStatus);
        return user;
    }
}
