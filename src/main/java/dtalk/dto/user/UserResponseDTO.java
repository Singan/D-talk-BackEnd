package dtalk.dto.user;

import dtalk.domain.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseDTO {
    private Long idx;
    private String id;
    private String nickname;
    private String profileImg;
    private Integer bgmStatus;
    private Boolean remove;

    public static UserResponseDTO createUserResDto(User user) {
        UserResponseDTO userResponseDto = new UserResponseDTO();
        userResponseDto.idx = user.getIdx();
        userResponseDto.id = user.getId();
        userResponseDto.nickname = user.getNickname();
        userResponseDto.profileImg = user.getProfileImg();
        userResponseDto.bgmStatus = user.getBgmStatus();
        userResponseDto.remove = user.getRemove();
        return userResponseDto;
    }

    public static List<UserResponseDTO> createUserResDto(List<User> userList) {
        List<UserResponseDTO> list= new ArrayList<UserResponseDTO>();
        for (User user : userList) {
            UserResponseDTO userResponseDto = new UserResponseDTO();
            userResponseDto.idx = user.getIdx();
            userResponseDto.id = user.getId();
            userResponseDto.nickname = user.getNickname();
            userResponseDto.profileImg = user.getProfileImg();
            userResponseDto.bgmStatus = user.getBgmStatus();
            userResponseDto.remove = user.getRemove();
            list.add(userResponseDto);

        }
        return list;
    }
}
