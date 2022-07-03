package dtalk.dto.user;

import dtalk.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class UserUpdateDTO {
    private String nickname;
    private String profileImg;
    private Integer bgmStatus;
    public static UserUpdateDTO createUserUpdateDTO(User user) {
        UserUpdateDTO userUpdateDTO =new UserUpdateDTO();
        userUpdateDTO.bgmStatus=user.getBgmStatus();
        userUpdateDTO.nickname = user.getNickname();
        userUpdateDTO.profileImg = user.getProfileImg();

        return userUpdateDTO;
    }
}
