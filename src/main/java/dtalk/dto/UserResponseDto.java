package dtalk.dto;

import dtalk.domain.Quiz;
import dtalk.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseDto {
    private Long idx;
    private String id;
    private String nickname;
    private String profileImg;
    private Integer bgmStatus;
    private Boolean remove;

    public static UserResponseDto createUserResDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.idx = user.getIdx();
        userResponseDto.id = user.getId();
        userResponseDto.nickname = user.getNickname();
        userResponseDto.profileImg = user.getProfileImg();
        userResponseDto.bgmStatus = user.getBgmStatus();
        userResponseDto.remove = user.getRemove();
        return userResponseDto;
    }

    public static List<UserResponseDto> createUserResDto(List<User> userList) {
        List<UserResponseDto> list= new ArrayList<UserResponseDto>();
        for (User user : userList) {
            UserResponseDto userResponseDto = new UserResponseDto();
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
