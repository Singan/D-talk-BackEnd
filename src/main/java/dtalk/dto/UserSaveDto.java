package dtalk.dto;

import dtalk.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserSaveDto {
    private String id;
    private String pw;
    private String nickname;
    private String profileImg;

    public User createUser() {
        User user = new User();
        user.setId(id);
        user.setPw(pw);
        user.setNickname(nickname);
        user.setProfileImg(profileImg);
        user.setBgmStatus(100);
        return user;
    }
}
