package dtalk.dto.user;

import dtalk.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFindDTO {
    private Long idx;
    private String id;
    private String nickname;
    private String profileImg;
    public static UserFindDTO createUserFindDto(User user){
        UserFindDTO userFindDto = new UserFindDTO(user.getIdx(), user.getId(), user.getNickname(), user.getProfileImg());
        return userFindDto;
    }
}
