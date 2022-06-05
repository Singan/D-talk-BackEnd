package dtalk.dto.user;

import dtalk.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserSaveDTO {
    @NotBlank(message = "아이디는 빈칸 일 수 없습니다.")
    private String id;
    @NotBlank(message = "비밀번호는 빈칸 일 수 없습니다.")
    @Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.")
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
