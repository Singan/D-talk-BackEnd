package dtalk.dto.user;

import dtalk.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Long idx;
    private String id;
    private String nickname;
    private String profileImg;
    private Integer bgmStatus;

    public static UserResponseDTO createUserResDto(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO(
                user.getIdx(),
                user.getId(),
                user.getNickname(),
                user.getProfileImg(),
                user.getBgmStatus());


        return userResponseDTO;
    }

    public static List<UserResponseDTO> createUserResDto(List<User> userList) {
        List<UserResponseDTO> list= new ArrayList<>();
        for (User user : userList) {
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    user.getIdx(),
                    user.getId(),
                    user.getNickname(),
                    user.getProfileImg(),
                    user.getBgmStatus());
            list.add(userResponseDTO);

        }
        return list;
    }
}
